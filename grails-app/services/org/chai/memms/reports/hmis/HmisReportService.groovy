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
package org.chai.memms.reports.hmis

import java.util.List;
import java.util.Map;
import org.chai.location.Location;
import org.chai.location.DataLocationType;
import org.chai.location.DataLocation;
import org.chai.memms.util.Utils;
import org.chai.memms.inventory.EquipmentService;
import org.chai.location.DataLocation;
import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus.Status;

/**
 * @author Eric Dusabe, Jean Kahigiso M.
 *
 */
class HmisReportService {

	static transactional = true
	def languageService;
	def locationService
	def grailsApplication
	def equipmentService

	private def generateHmisReportName() {
		def today = new Date()
		def map
		if(today.month == 4 && today.day == 1 ) 
			map = ["periodCode":"Q1-"+today.year, "names":["en":"First Quarter", "fr":"Premier Trimestre"]]
		else if(today.month == 7 && today.day == 1 ) 
			map = ["periodCode":"Q2-"+today.year, "names":["en":"Second Quarter", "fr":"Deuxieme Trimestre"]]
		else if(today.month == 10 && today.day == 1 ) 
			map = ["periodCode":"Q3-"+today.year, "names":["en":"Third Quarter", "fr":"Troisieme Trimestre"]]
		else if(today.month == 1 && today.day == 1 )
			map = ["periodCode":"Q4-"+today.year,"names":[ "en":"Fourth Quarter", "fr":"Quatrieme Trimestre"]]
		else
			map = ["periodCode":today.toString(), "names":["en":"Report from " + today.toString(),"fr":"Rapport du " + today.toString()]]
		
		return map
	}

	public def generateHmisReport() {
		def reportMap = generateHmisReportName()
		def hmisReport = newHmisReport(reportMap.get("periodCode"), reportMap.get("names"))
		def dataLocations = equipmentService.getAllDataLocationsWithEquipment()
		def hmisEquipmentTypes = HmisEquipmentType.list()

		if(hmisReport) {
			for(def dataLocation: dataLocations) {
				for(def hmisEquipmentType: hmisEquipmentTypes) {
					def criteria = Equipment.createCriteria()
					def numberOfOpEquipment = 0
					//Even if the equipment exist a give site, if it type is not associate to any hmis type, 
					//the equipment will never be counted in the report
					if(hmisEquipmentType.equipmentTypes.size()>0){
						numberOfOpEquipment = criteria.get{
							projections {
								rowCount()
							}
							and{
								'in'("currentStatus", [Status.OPERATIONAL,Status.INSTOCK])
								'in'("type", hmisEquipmentType.equipmentTypes)
								eq("dataLocation",dataLocation)
							}
						}
					}
					newHmisFacilityReport(hmisReport, dataLocation, hmisEquipmentType, numberOfOpEquipment)
				}	
			}
		}
	}
	
	private def newHmisReport(def code, def names) {
		def hmisReport = new HmisReport(periodCode:code)
		Utils.setLocaleValueInMap(hmisReport,names,"Names")
		return hmisReport.save(failOnError:true,flush:true)
	}

	private def newHmisFacilityReport(def hmisReport, def dataLocation, def hmisEquipmentType, def numberOfOpEquipment) {
		def hmisFacilityReport = new HmisFacilityReport(dataLocation:dataLocation, hmisEquipmentType:hmisEquipmentType, numberOfOpEquipment:numberOfOpEquipment)
		hmisReport.addToHmisFacilityReports(hmisFacilityReport)
		return hmisReport.save(failOnError:true,flush:true)
	}

}