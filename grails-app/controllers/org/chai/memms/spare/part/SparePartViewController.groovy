/** 
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.chai.memms.spare.part

import java.util.Set;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import org.chai.location.CalculationLocation;
import org.chai.location.DataLocation;
import org.chai.location.DataLocationType;
import org.chai.location.Location
import org.chai.location.LocationLevel

import org.chai.memms.AbstractController;
import org.chai.memms.spare.part.SparePart;
import org.chai.memms.spare.part.SparePartType;
import org.chai.memms.inventory.Provider;
import org.chai.memms.spare.part.SparePart.SparePartPurchasedBy;
import org.chai.memms.spare.part.SparePartStatus.StatusOfSparePart;


import org.chai.memms.inventory.Provider;

import org.chai.memms.security.User;
import org.chai.memms.security.User.UserType;
import org.chai.task.SparePartExportFilter;

/**
 * @author Aphrodice Rwagaju
 *
 */
class SparePartViewController extends AbstractController{
	def providerService
	def partService
	def sparePartStatusService
	def sparePartService
	def grailsApplication

	def getLabel() {
		return "spare.part.label";
	}

	def getEntityClass() {
		return SparePart.class;
	}
	
	def getSparePartClueTipsAjaxData = {
		def sparePart = SparePart.get(params.long("sparePart.id"))
		def html = g.render(template:"/templates/sparePartClueTip",model:[sparePart:sparePart])
		render(contentType:"text/plain", text:html)
	}
	
	def list = {
		adaptParamsForList()
		def spareParts = sparePartService.getSparePartsByUser(user,params)
		
		if(request.xhr)
			this.ajaxModel(spareParts,"")
		else{
		render(view:"/entity/list",model:[
				template:"sparePart/sparePartList",
				listTop:"sparePart/listTop",
				entities: spareParts,
				entityCount: spareParts.totalCount,
				entityClass: getEntityClass(),
				code: getLabel(),
				names:names,
				//exportTask:'EquipmentTypeExportTask'
			])
		}
	}
	
	//TODO don't think we need ajax for this
	def selectFacility = {
		adaptParamsForList()
		def locations = []
		locations.add(user.location as DataLocation)
		if((user.location as DataLocation).manages)
			locations.addAll((user.location as DataLocation).manages)
			
		render(view:"/entity/list", model:[
					listTop:"sparePart/listTop",
					template:"sparePart/selectFacility",
					locations:locations
				])
	}
	
	def search = {
		adaptParamsForList()
		def spareParts = sparePartService.searchSparePart(params['q'],user,params)
		if(!request.xhr)
			render(view:"/entity/list", model: model(spareParts) << [
				template:"sparePart/sparePartList",
				filterTemplate:"sparePart/sparePartFilter",
				listTop:"sparePart/listTop"
			])
		else
			this.ajaxModel(spareParts,params['q'])
	}
	
	def filter = { FilterCommand cmd ->
		if (log.isDebugEnabled()) log.debug("spareParts.filter, command "+cmd)
		if (cmd.location != null && !user.canAccessCalculationLocation(cmd.location))
			response.sendError(404)
		else{
			adaptParamsForList()
			def spareParts = sparePartService.filterSparePart(user,cmd.location,cmd.supplier,cmd.sparePartType,cmd.sparePartPurchasedBy,cmd.sameAsManufacturer,cmd.status,params)
			if(!request.xhr)
				response.sendError(404)
			else this.ajaxModel(spareParts,cmd.location,"")
		}
	}
	
	def ajaxModel(def entities,def searchTerm) {
		def model = [entities: entities,entityCount: entities.totalCount,entityClass:getEntityClass(),names:names,q:searchTerm]
		def listHtml = g.render(template:"/entity/sparePart/sparePartList",model:model)
		render(contentType:"text/json") { results = [listHtml] }
	}
	def model(def entities) {
		return [
			entities: entities,
			entityCount: entities.totalCount,
			code: getLabel()
		]
	}
	
	def summaryPage = {
		if(user.location instanceof DataLocation) redirect(controller:"sparePartView",action:"list")

		def location = DataLocation.get(params.long('dataLocation'))
		def locationTypesFilter = getLocationTypes()
		def template = null
		def spareParts = null

		adaptParamsForList()

		def locationSkipLevels = partService.getSkipLocationLevels()

		if (location != null)
			spareParts = partService.getsparePartByLocation(location,locationTypesFilter,params)
	
		render (view: '/partSummaryPage/summaryPage', model: [
					spareParts:spareParts?.sparePartList,
					currentLocation: location,
					currentLocationTypes: locationTypesFilter,
					template: "/partSummaryPage/sectionTable",
					entityCount: spareParts?.totalCount,
					locationSkipLevels: locationSkipLevels
				])
	}
	def updateSameAsManufacturer = {
		if (log.isDebugEnabled()) log.debug("updateSameAsManufacturer sparePart.sameAsManufacturer "+params['sparePart.id'])
		SparePart sparePart = SparePart.get(params.int(['sparePart.id']))
		def property = params['field'];
		if (sparePart == null || property ==null)
			response.sendError(404)
		else {
			def value= false; def entity = null;
			if(property.equals("sameAsManufacturer")){
				if(sparePart.sameAsManufacturer) sparePart.sameAsManufacturer = false
				else {
					sparePart.lastModified = user
					sparePart.sameAsManufacturer = true
				}
				entity = sparePart.save(flush:true)

			}
			if(entity!=null) value=true
			render(contentType:"text/json") { results = [value]}
		}
	}

	def getAjaxData = {
		List<SparePart> spareParts = sparePartService.searchSparePart(params['term'],null,[:])
		render(contentType:"text/json") {
			elements = array {
				spareParts.each { sparePart ->
					elem (
							key: sparePart.id,
							value: sparePart.code
							)
				}
			}
			htmls = array {
				spareParts.each { sparePart ->
					elem (
							key: sparePart.id,
							html: g.render(template:"/templates/sparePartFormSide",model:[sparePart:sparePart,label:label,cssClass:"form-aside-hidden",field:"sparePart"])
							)
				}
			}
		}

	}

}
class FilterCommand {
	DataLocation location
	SparePartType sparePartType
	Provider supplier
	StatusOfSparePart status = StatusOfSparePart.NONE
	SparePartPurchasedBy sparePartPurchasedBy
	String sameAsManufacturer
	 

	public boolean getSameAsManufacturerStatus(){
		if(sameAsManufacturer) return null
		else if(sameAsManufacturer.equals("true")) return true
		else if(sameAsManufacturer.equals("false")) return false
	}

	static constraints = {

		sparePartType nullable:true
		supplier nullable:true
		status nullable:true
		sparePartPurchasedBy nullable:true
		sameAsManufacturer nullable:true

		location nullable:false, validator:{val, obj ->
			return (obj.sparePartType != null || obj.supplier != null || (obj.status != null && obj.status != StatusOfSparePart.NONE) || obj.sparePartPurchasedBy || obj.sameAsManufacturer)?true:"select.atleast.one.value.text"
		}
	}

	String toString() {
		return "FilterCommand[DataLocation="+location+", SparePartType="+sparePartType+
		", Supplier="+supplier+", StatusOfSparePart="+status+", donated="+sparePartPurchasedBy+", sameAsManufacturer="+sameAsManufacturer+
		"]"
	}
}

class ExportFilterCommand {
	Set<CalculationLocation> calculationLocations
	Set<DataLocationType> locationTypes
	Set<SparePartType> sparePartTypes
	Set<Provider> suppliers
	StatusOfSparePart sparePartStatus
	SparePartPurchasedBy sparePartPurchasedBy
	String sameAsManufacturer
	 

	public boolean getSameAsManufacturerStatus(){
		if(sameAsManufacturer) return null
		else if(sameAsManufacturer.equals("true")) return true
		else if(sameAsManufacturer.equals("false")) return false
	}

	static constraints = {
		calculationLocations nullable:true
		locationTypes nullable:true
		sparePartTypes nullable:true
		suppliers nullable:true
		sparePartStatus nullable:true
		sparePartPurchasedBy nullable:true
		sameAsManufacturer nullable:true
	}

	String toString() {
		return "ExportFilterCommand[ CalculationLocations="+calculationLocations+", DataLocationTypes="+locationTypes+" , SparePartTypes="+sparePartTypes+
		", Suppliers="+suppliers+", StatusOfSparePart="+sparePartStatus+", donated="+sparePartPurchasedBy+", sameAsManufacturer="+sameAsManufacturer+
		"]"
	}

}
