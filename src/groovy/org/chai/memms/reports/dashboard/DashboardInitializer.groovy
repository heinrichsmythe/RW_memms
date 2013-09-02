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
package org.chai.memms.reports.dashboard
import org.chai.memms.util.Utils;
/**
 * @author Antoine Nzeyi, Donatien Masengesho, Pivot Access Ltd
 *
 */
class DashboardInitializer {

    //indicator category codes
    public static final String CORRECTIVE_MAINTENANCE="corrective"
    public static final String PRIVENTIVE_MAINTENANCE="preventive"
    public static final String MANAGEMENT_SPARE_PARTS="spare_parts"
    public static final String MANAGEMENT_EQUIPMENT="equipment"
    public static final String MONITORING_MEMMS_USE="monitoring"
    //Indicator computation scripts
    //Slid 7:Share of operational equipment
    public static final String SHARE_OPERATIONAL_SIMPLE_SLD7="select 1.0*count(equ.id)/(select count(equIn.id) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus='OPERATIONAL' and equ.dataLocation @DATA_LOCATION"
    public static final String SHARE_OPERATIONAL_GROUP_SLD7="select "+Utils.buildSubQueryLanguages("equ.type.names")+",1.0*count(equ.id)/(select count(equIn.id) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus='OPERATIONAL' and equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 8:Share of equipment for disposal
    // public static final String SHARE_DISPOSAL_SIMPLE_SLD8="select 1.0*count(equ.id)/(select count(equIn.id) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus='FORDISPOSAL' and equ.dataLocation @DATA_LOCATION"
    // public static final String SHARE_DISPOSAL_GROUP_SLD8="select equ.type.names_en,1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus='FORDISPOSAL' and equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 9:Share of equipment in stock
    // public static final String SHARE_STOCK_SIMPLE_SLD9="select 1.0*count(equ.id)/(select count(equIn.id) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus='INSTOCK' and equ.dataLocation @DATA_LOCATION"
    // public static final String SHARE_STOCK_GROUP_SLD9="select equ.type.names_en,1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus='INSTOCK' and equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 10:Degree of equipment standardization
    // public static final String DEGREE_STANDARDIZATION_SIMPLE_SLD10="select sum(tmp2.max_eq)/tmp3.denominator as final_result from (select max(tmp1.counter) as max_eq from (select type_id, manufacturer_id, count(id) as counter FROM memms_equipment where current_status not in ('FORDISPOSAL','DISPOSED') and type_id is not null and manufacturer_id is not null and data_location_id @DATA_LOCATION group by type_id, manufacturer_id) tmp1 group by tmp1.type_id) tmp2, (select sum(temp22.counter3) as denominator from (select e.type_id as typeid,count(e.id) as counter3 from memms_equipment e,memms_equipment_type et where e.type_id=et.id and current_status not in ('FORDISPOSAL','DISPOSED') and data_location_id @DATA_LOCATION group by e.type_id,e.manufacturer_id) temp22) tmp3"
    // public static final String DEGREE_STANDARDIZATION_GROUP_SLD10="select tmp2.typeNames as typeName, tmp2.max_eq/tmp3.denominator as final_result from (select tmp1.namess as typeNames,max(tmp1.counter1) as max_eq from (select ee.type_id,et.names_en as namess, ee.manufacturer_id, count(ee.id) as counter1 FROM memms_equipment ee,memms_equipment_type et where ee.type_id=et.id and ee.type_id is not null and ee.manufacturer_id is not null and ee.current_status not in ('FORDISPOSAL','DISPOSED') and ee.data_location_id @DATA_LOCATION group by ee.type_id, ee.manufacturer_id) tmp1 group by tmp1.type_id) tmp2,(select sum(temp2.counter3) as denominator from (select eq.type_id as typeid,count(eq.id) as counter3 from memms_equipment eq,memms_equipment_type et where eq.type_id=et.id and current_status not in ('FORDISPOSAL','DISPOSED') and data_location_id @DATA_LOCATION group by eq.type_id,eq.manufacturer_id) temp2) tmp3"
    // //Slie 11:Share of equipment donated
    // public static final String SHARE_DONATED_SIMPLE_SLD11="select 1.0*count(equ.id)/(select count(equIn.id) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equ.purchaser='BYDONOR' and equ.dataLocation @DATA_LOCATION"
    // public static final String SHARE_DONATED_GROUP_SLD11="select equ.type.names_en,1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equ.purchaser='BYDONOR' and equ.dataLocation @DATA_LOCATION group by equ.type"
    //Slie 12:Share of obsolete equipment
    public static final String SHARE_OBSOLETE_SIMPLE_SLD12="select 1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equ.obsolete=1 and equ.dataLocation @DATA_LOCATION"
    public static final String SHARE_OBSOLETE_GROUP_SLD12="select "+Utils.buildSubQueryLanguages("equ.type.names")+",1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equ.obsolete=1 and equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 13:Share of equipment under active warranty or an active service provider contract
    // public static final String SHARE_ACTIVE_WARANTY_SIMPLE_SLD13="select count(equ.code)/(select count(f.code) from memms_equipment f where f.current_status in ('OPERATIONAL','PARTIALLYOPERATIONAL','UNDERMAINTENANCE','INSTOCK') and f.data_location_id @DATA_LOCATION) as final_result from memms_equipment as equ where equ.current_status in ('OPERATIONAL','UNDERMAINTENANCE','PARTIALLYOPERATIONAL','INSTOCK') and (dateDiff(NOW(),equ.warranty_start_date)<(equ.warranty_period_number_of_months)*30 or dateDiff(NOW(),equ.service_contract_start_date)<(equ.service_contract_period_number_of_months)*30) and equ.data_location_id @DATA_LOCATION"
    // public static final String SHARE_ACTIVE_WARANTY_GROUP_SLD13="select et.names_en,count(equ.code)/(select count(f.code) from memms_equipment f where f.current_status in ('OPERATIONAL','PARTIALLYOPERATIONAL','UNDERMAINTENANCE','INSTOCK') and f.data_location_id @DATA_LOCATION) as final_result from memms_equipment as equ,memms_equipment_type et where equ.type_id=et.id and equ.current_status in ('OPERATIONAL','UNDERMAINTENANCE','PARTIALLYOPERATIONAL','INSTOCK') and (dateDiff(NOW(),equ.warranty_start_date)<(equ.warranty_period_number_of_months)*30 or dateDiff(NOW(),equ.service_contract_start_date)<(equ.service_contract_period_number_of_months)*30) and equ.data_location_id @DATA_LOCATION group by equ.type_id"
    // //Slie 14:Share of equipment with lifetime expiring in less than 2 years
    // public static final String SHARE_LIFETIME_LESS_SIMPLE_SLD14="select 1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and ((dateDiff(NOW(),equ.purchaseDate)/12)>(equ.expectedLifeTime-24)) and equ.dataLocation @DATA_LOCATION"
    // public static final String SHARE_LIFETIME_LESS_GROUP_SLD14="select equ.type.names_en, 1.0*count(equ.code)/(select count(equIn.code) as count1 from Equipment as equIn where equIn.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and equIn.dataLocation @DATA_LOCATION) from Equipment as equ where equ.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and ((dateDiff(NOW(),equ.purchaseDate)/12)>(equ.expectedLifeTime-24)) and equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 15:Share of equipments for which a work order was generated=>rev ok
    // public static final String SHARE_WORK_ORDER_GEN_SIMPLE_SLD15="select 1.0*count(equ.code)/(select count(f.code) from Equipment f where f.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and f.dataLocation @DATA_LOCATION)  from WorkOrder wo join wo.equipment equ where wo.equipment.id is not null and equ.dataLocation @DATA_LOCATION"
    // public static final String SHARE_WORK_ORDER_GEN_GROUP_SLD15="select wo.equipment.type.names_en,1.0*count(wo.equipment.id)/(select count(f.code) from Equipment f where f.currentStatus in ('OPERATIONAL','PARTIALLYOPERATIONAL', 'UNDERMAINTENANCE') and f.dataLocation @DATA_LOCATION)  from WorkOrder wo  where wo.equipment.id is not null and wo.equipment.dataLocation @DATA_LOCATION group by  wo.equipment.type"
    //Slie 16:Degree of corrective maintenance execution according to benchmark=rev ok
    public static final String DEGREE_CORRECTIVE_EX_SIMPLE_SLD16="select 1.0*count(wo1.id)/(select count(wo.id) from WorkOrderStatus wos join wos.workOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) from WorkOrderStatus wos1 join wos1.workOrder wo1 join wo1.equipment as equ1 where wo1.currentStatus='CLOSEDFIXED' and (dateDiff(NOW(),wo1.returnedOn) < #DEGGREE_CORRE_MAINT_EXECUTION or dateDiff(NOW(),wos1.dateCreated) < #DEGGREE_CORRE_MAINT_EXECUTION) and equ1.dataLocation @DATA_LOCATION"
    //time frame to be decided by the user
    public static final String DEGREE_CORRECTIVE_EX_GROUP_SLD16="select equ1.type.names_en as names,1.0*count(wo1.id)/(select count(wo.id) from WorkOrderStatus wos join wos.workOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) as final_result from WorkOrderStatus wos1 join wos1.workOrder wo1 join wo1.equipment as equ1 where wo1.currentStatus='CLOSEDFIXED' and (dateDiff(NOW(),wo1.returnedOn) < #DEGGREE_CORRE_MAINT_EXECUTION or dateDiff(NOW(),wos1.dateCreated) < #DEGGREE_CORRE_MAINT_EXECUTION) and equ1.dataLocation @DATA_LOCATION group by equ1.type"
    // //Slie 17:Share of work orders witnessing re-incidence in a period of time (user defined)
    // public static final String SHARE_WORK_ORDER_WITNESSING_SIMPLE_SLD17="select numerator.reincidenceorders/denominator.denominatorRes as final_result from (select count(woo.equipment_id) as reincidenceorders,st.contact_contact_name as providername from memms_equipment equ,memms_provider st,memms_work_order woo  where (equ.service_provider_id=st.id or equ.supplier_id=st.id or equ.manufacturer_id=st.id ) and equ.id=woo.equipment_id and dateDiff(NOW(),woo.open_on)<=#WO_REINCIDENCE_DAYS and equ.data_location_id @DATA_LOCATION)numerator,(select count(woo1.equipment_id) as denominatorRes from memms_equipment equ1,memms_work_order woo1  where equ1.id=woo1.equipment_id and dateDiff(NOW(),woo1.open_on)<=#WO_REINCIDENCE_DAYS and equ1.data_location_id @DATA_LOCATION) denominator where numerator.reincidenceorders >=2"
    // public static final String SHARE_WORK_ORDER_WITNESSING_GROUP_SLD17="select numerator.providername,numerator.reincidenceorders/denominator.denominatorRes as final_result from (select count(woo.equipment_id) as reincidenceorders,st.contact_contact_name as providername from memms_equipment equ,memms_provider st,memms_work_order woo  where (equ.service_provider_id=st.id or equ.supplier_id=st.id or equ.manufacturer_id=st.id ) and equ.id=woo.equipment_id and dateDiff(NOW(),woo.open_on)<=#WO_REINCIDENCE_DAYS and equ.data_location_id @DATA_LOCATION group by st.id)numerator,(select count(woo1.equipment_id) as denominatorRes from memms_equipment equ1,memms_work_order woo1 where equ1.id=woo1.equipment_id and dateDiff(NOW(),woo1.open_on)<=#WO_REINCIDENCE_DAYS and equ1.data_location_id @DATA_LOCATION) denominator where numerator.reincidenceorders>=2"
    // //Slie 18:Cost effectiveness of corrective maintenance =>REV OK
    // public static final String COST_EFF_CORR_SIMPLE_SLD18="select avg(temp_query.totalOrders) as final_result from (select sum(woo.estimated_cost)/equ.purchase_cost as totalOrders from memms_equipment equ,memms_work_order woo  where equ.id=woo.equipment_id and equ.purchase_cost is not null and woo.estimated_cost is not null and equ.data_location_id @DATA_LOCATION group by woo.equipment_id) temp_query"
    // public static final String COST_EFF_CORR_GROUP_SLD18="select temp_query.typename,avg(temp_query.totalOrders) as final_result from (select sum(woo.estimated_cost)/equ.purchase_cost as totalOrders,st.id as typeId,st.names_en as typename from memms_equipment equ,memms_equipment_type st,memms_work_order woo  where equ.type_id=st.id and equ.id=woo.equipment_id and equ.purchase_cost is not null and woo.estimated_cost is not null and equ.data_location_id @DATA_LOCATION group by woo.equipment_id) temp_query where temp_query.totalOrders>0 group by temp_query.typeId"
    // //Slie 19:Share of work orders escalated to MMC =>REV OK
    // public static final String SHARE_ESCLATED_MMC_SIMPLE_SLD19="select 1.0*count(wo.id)/(select count(wo.id) from WorkOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) from WorkOrder wo join wo.equipment as equ where wo.currentStatus='OPENATMMC' and equ.dataLocation @DATA_LOCATION"
    // public static final String SHARE_ESCLATED_MMC_GROUP_SLD19="select equ.type.names_en,1.0*count(wo.id)/(select count(wo.id) from WorkOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) from WorkOrder wo join wo.equipment as equ where wo.currentStatus='OPENATMMC' and equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 20:Average time to fix equipments
    // public static final String AVGE_FIX_SIMPLE_SLD20="select avg(temp.fromOpenToClosed) as final_result from (select dateDiff(wo.open_on,wos.date_created) as fromOpenToClosed from memms_work_order wo ,memms_equipment equ,memms_work_order_status wos  where wo.equipment_id=equ.id and wo.id=wos.work_order_id and wo.current_status='CLOSEDFIXED' and wos.status='OPENATFOSA' and equ.data_location_id @DATA_LOCATION) temp"
    // public static final String AVGE_FIX_GROUP_SLD20="select temp.typeName,avg(temp.fromOpenToClosed) as final_result from (select dateDiff(wo.open_on,wos.date_created) as fromOpenToClosed , ty.names_en as typeName,ty.id as typeId from memms_work_order wo ,memms_equipment equ,memms_equipment_type ty,memms_work_order_status wos  where equ.type_id=ty.id and wo.equipment_id=equ.id and wo.id=wos.work_order_id and wo.current_status='CLOSEDFIXED' and wos.status='OPENATFOSA' and equ.data_location_id @DATA_LOCATION) temp group by temp.typeId"
    // //Slie 21:Average time to fix equipments by the District Hospital technician
    // public static final String AVGE_TIME_FIX_DH_SIMPLE_SLD21="select avg(temp.fromOpenToClosed) as final_result from (select dateDiff(wos.date_created,wo.open_on) as fromOpenToClosed from memms_work_order wo ,memms_equipment equ,memms_work_order_status wos  where wo.equipment_id=equ.id and wo.id=wos.work_order_id and wo.current_status='CLOSEDFIXED' and wo.id not in(select wo.id from memms_work_order wo ,memms_equipment equ,memms_work_order_status wos  where wo.equipment_id=equ.id and wo.id=wos.work_order_id and wos.status='OPENATMMC' and equ.data_location_id @DATA_LOCATION) and equ.data_location_id @DATA_LOCATION) temp"
    // public static final String AVGE_TIME_FIX_DH_GROUP_SLD21="select temp.nameEn,avg(temp.fromOpenToClosed) as final_result from (select dateDiff(wos.date_created,wo.open_on) as fromOpenToClosed,ty.id as typeId,ty.names_en as nameEn from memms_work_order wo ,memms_equipment equ,memms_equipment_type ty,memms_work_order_status wos  where equ.type_id=ty.id and wo.equipment_id=equ.id and wo.id=wos.work_order_id and wo.current_status='CLOSEDFIXED' and wo.id not in(select wo.id from memms_work_order wo ,memms_equipment equ,memms_work_order_status wos  where wo.equipment_id=equ.id and wo.id=wos.work_order_id and wos.status='OPENATMMC' and equ.data_location_id @DATA_LOCATION) and equ.data_location_id @DATA_LOCATION) temp group by temp.typeId"
    // //Slie 22:Average time to fix equipments by MMC
    // public static final String AVGE_TIME_FIX_MMC_SIMPLE_SLD22="select avg(temp.fromOpenToClosed) as final_result from (select dateDiff(wo.open_on,wos.date_created) as fromOpenToClosed from memms_work_order wo ,memms_equipment equ,memms_work_order_status wos  where wo.equipment_id=equ.id and wo.id=wos.work_order_id and wo.current_status='CLOSEDFIXED' and wos.status='OPENATMMC' and equ.data_location_id @DATA_LOCATION) temp"
    // public static final String AVGE_TIME_FIX_MMC_GROUP_SLD22="select temp.nameEn,avg(temp.fromOpenToClosed) as final_result from (select dateDiff(wo.open_on,wos.date_created) as fromOpenToClosed,ty.names_en as nameEn,ty.id as typeId from memms_work_order wo ,memms_equipment equ,memms_equipment_type ty,memms_work_order_status wos  where equ.type_id=ty.id and wo.equipment_id=equ.id and wo.id=wos.work_order_id and wo.current_status='CLOSEDFIXED' and wos.status='OPENATMMC' and equ.data_location_id @DATA_LOCATION) temp group by temp.typeId;"
    // //Slie 23:Share of work orders requiring new spare parts
    // public static final String SHARE_WORK_ORDER_REQ_SP_SIMPLE_SLD23=""
    // public static final String SHARE_WORK_ORDER_REQ_SP_GROUP_SLD23=""
    // //Slie 24:Share of problems caused by misuse
    // public static final String SHARE_PROBLEM_MISUSE_SIMPLE_SLD24="select 1.0*count(wo.id)/(select count(wo.id) from WorkOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) from WorkOrder wo join wo.equipment as equ where wo.failureReason='MISUSE' and equ.dataLocation @DATA_LOCATION"
    // public static final String SHARE_PROBLEM_MISUSE_GROUP_SLD24="select equ.type.names_en,1.0*count(wo.id)/(select count(wo.id) from WorkOrder wo join wo.equipment as equ where equ.dataLocation @DATA_LOCATION) from WorkOrder wo join wo.equipment as equ where wo.failureReason='MISUSE' and equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 26:Degree of execution of preventive maintenance
     public static final String DEGREE_EXCUTION_PREV_SIMPLE_SLD26="select 1.0*count(prevention.id)/(select count(prwo.id) FROM PreventiveOrder prwo join prwo.equipment as equ1  where equ1.dataLocation @DATA_LOCATION) FROM Prevention prevention join prevention.order as pOrder join pOrder.equipment as equ where equ.dataLocation @DATA_LOCATION"
     public static final String DEGREE_EXCUTION_PREV_GROUP_SLD26="select "+Utils.buildSubQueryLanguages("equ.type.names")+",1.0*count(prevention.id)/(select count(prwo.id) FROM PreventiveOrder prwo join prwo.equipment as equ1  where equ1.dataLocation @DATA_LOCATION) FROM Prevention prevention  join prevention.order as pOrder join pOrder.equipment as equ where equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 27:Share of maintenance executed at the facility
    // public static final String SHARE_MAIN_EXC_SIMPLE_SLD27="select 1.0*count(prwo.id)/(select count(prwo.id) FROM PreventiveOrder prwo join prwo.equipment as equ1  where equ1.dataLocation @DATA_LOCATION) FROM Prevention pr join pr.order as prwo join prwo.equipment as equ where equ.dataLocation @DATA_LOCATION"
    // public static final String SHARE_MAIN_EXC_GROUP_SLD27="select equ.type.names_en,1.0*count(prwo.id)/(select count(prwo.id) FROM PreventiveOrder prwo join prwo.equipment as equ1  where equ1.dataLocation @DATA_LOCATION) FROM Prevention pr join pr.order as prwo join prwo.equipment as equ where equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 28:Deviation against scheduled date
    // public static final String DEVIATION_AGAIST_SIMPLE_SLD28="select ABS(avg(dateDiff(pr.eventDate,pr.scheduledOn))) FROM Prevention pr join pr.order as prwo join prwo.equipment as equ where equ.dataLocation @DATA_LOCATION"
    // public static final String DEVIATION_AGAIST_GROUP_SLD28="select equ.type.names_en, ABS(avg(dateDiff(pr.eventDate,pr.scheduledOn))) FROM Prevention pr join pr.order as prwo join prwo.equipment as equ where equ.dataLocation @DATA_LOCATION group by equ.type"
    // //Slie 29:Number of types of spare parts about to be discontinued in a year
    // public static final String NUMBER_TYPE_SPARE_DISC_SIMPLE_SLD29="select count(sp.id) FROM SparePart sp join sp.type as sptp where dateDiff(sptp.discontinuedDate,NOW())<=365 and sp.dataLocation @DATA_LOCATION"
    // public static final String NUMBER_TYPE_SPARE_DISC_GROUP_SLD29="select sptp.names_en,count(sp.id) FROM SparePart sp join sp.type as sptp where dateDiff(sptp.discontinuedDate,NOW())<=365 and sp.dataLocation @DATA_LOCATION group by sptp.id"
    // //Slie 30:Share of types of spare parts about to stock out in a given time period
    // public static final String SHARE_TYPE_SP_PART_STOCK_OUT_SIMPLE_SLD30="select count(temp.spTypeTimount)/(SELECT count(mmm.id)  FROM memms_spare_part mmm where mmm.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.type_id)/(SELECT ROUND((count(m.id)/12),0)  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as spTypeTimount   FROM memms_spare_part mm where mm.data_location_id @DATA_LOCATION group by mm.type_id) temp where temp.spTypeTimount<#TRASHHOLD_MIN_STOCT_OUT"
    // public static final String SHARE_TYPE_SP_PART_STOCK_OUT_GROUP_SLD30="select temp.descen,temp.spTypeTimount/(SELECT count(mmm.id)  FROM memms_spare_part mmm where mmm.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.type_id)/(SELECT ROUND(count(m.id)/12)  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as spTypeTimount,spt.names_en as descen   FROM memms_spare_part mm,memms_spare_part_type spt where mm.type_id=spt.id and mm.data_location_id @DATA_LOCATION group by mm.type_id) temp where temp.spTypeTimount<#TRASHHOLD_MIN_STOCT_OUT"
    // //Slie 31:Number of types of spare parts with stock more than a given time period
    // public static final String NUMBER_SP_PAT_STOCK_MORE_SIMPLE_SLD31="select count(temp.spTypeTimount)/(SELECT count(mmm.id)  FROM memms_spare_part mmm where mmm.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.type_id)/(SELECT ROUND((count(m.id)/12),0)  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as spTypeTimount   FROM memms_spare_part mm where mm.data_location_id @DATA_LOCATION group by mm.type_id) temp where temp.spTypeTimount>#TRASHHOLD_MAX_STOCT_OUT"
    // public static final String NUMBER_SP_PAT_STOCK_MORE_GROUP_SLD31="select temp.namesEn,temp.spTypeTimount/(SELECT count(mmm.id)  FROM memms_spare_part mmm where mmm.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.type_id)/(SELECT count(m.id)/12  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as spTypeTimount,spt.names_en as namesEn FROM memms_spare_part mm,memms_spare_part_type spt where mm.type_id=spt.id and mm.data_location_id @DATA_LOCATION group by mm.type_id) temp where temp.spTypeTimount>#TRASHHOLD_MAX_STOCT_OUT"
    // //Slie 32:Average stock time
    // public static final String AVG_STOCK_TIME_SIMPLE_SLD32="select sum(temp.usageeach)/(SELECT count(m.id)  FROM memms_spare_part m where m.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.id)/(SELECT ROUND((count(m.id)/12),0)  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as usageeach FROM memms_spare_part mm where mm.data_location_id @DATA_LOCATION group by mm.type_id) temp"
    // public static final String AVG_STOCK_TIME_GROUP_SLD32="select temp.namesEn,temp.usageeach/(SELECT count(m.id)  FROM memms_spare_part m where m.data_location_id @DATA_LOCATION) as final_result from (SELECT count(mm.id)/(SELECT count(m.id)/12  FROM memms_spare_part m where m.status='OPERATIONAL' and dateDiff(NOW(),m.delivery_date)<=365 and m.data_location_id @DATA_LOCATION group by mm.type_id) as usageeach,spt.names_en as namesEn FROM memms_spare_part mm,memms_spare_part_type spt where mm.type_id=spt.id and mm.data_location_id @DATA_LOCATION group by mm.type_id) temp"
    // //Slie 33:Average time between request and arrival of spare parts
    // public static final String AVG_REQUEST_ARVAL_SIMPLE_SLD33="select ABS(ROUND(avg(dateDiff(sp.dateCreated,spst.dateOfEvent)),0)) from SparePartStatus spst join spst.sparePart as sp where sp.status='INSTOCK' and spst.statusOfSparePart='PENDINGORDER' and sp.dataLocation @DATA_LOCATION"
    // public static final String AVG_REQUEST_ARVAL_GROUP_SLD33="select sp.type.names_en, ABS(ROUND(avg(dateDiff(sp.dateCreated,spst.dateOfEvent)),0)) from SparePartStatus spst join spst.sparePart as sp where sp.status='INSTOCK' and spst.statusOfSparePart='PENDINGORDER' and sp.dataLocation @DATA_LOCATION group by sp.type.id"
    // //Slie 34:Average number of equipments per facility
    // public static final String AVG_NUM_EQU_FAC_SIMPLE_SLD34="select avg (temp1.count1) from (select count(equIn.id) as count1 from memms_equipment as equIn,chai_location_abstract c,chai_location_data_location cd where  equIn.data_location_id @DATA_LOCATION and c.id=cd.location group by cd.location,equIn.data_location_id) temp1"
    // public static final String AVG_NUM_EQU_FAC_GROUP_SLD34="select temp1.namess,avg (temp1.count1) from (select count(equIn.id) as count1, c.names_en as namess,c.id as facility from memms_equipment as equIn,chai_location_abstract c,chai_location_data_location cd where  equIn.data_location_id @DATA_LOCATION and c.id=cd.location group by cd.location,equIn.data_location_id) temp1  group by temp1.facility"
    // //Slie 35:Share of facilities with less than 10 inventory status changes in a year
    // public static final String SHARE_FAC_INVTORY_LESS_SIMPLE_SLD35="select count(temp1.counts_total)/(select count(loc.id) as total_fac FROM chai_location_data_location loc where loc.id @DATA_LOCATION) as final_result from (SELECT count(equ.data_location_id) as counts_total FROM memms_equipment equ,memms_equipment_status equst  where equ.id=equst.equipment_id and equ.data_location_id @DATA_LOCATION and dateDiff(NOW(),equst.date_of_event) <=365 group by equ.data_location_id) temp1 where temp1.counts_total<10;"
    // public static final String SHARE_FAC_INVTORY_LESS_GROUP_SLD35="select temp1.nameType,count(temp1.counts_total)/(select count(loc.id) as total_fac FROM chai_location_data_location loc where loc.id @DATA_LOCATION) as final_result from (SELECT count(equ.data_location_id) as counts_total,locTy.id as typeId,locTy.names_en as nameType FROM memms_equipment equ,chai_location_data_location locc,chai_location_data_location_type locTy,memms_equipment_status equst  where equ.data_location_id=locc.id and locc.type=locTy.id and equ.id=equst.equipment_id and equ.data_location_id @DATA_LOCATION and dateDiff(NOW(),equst.date_of_event) <=365 group by equ.data_location_id) temp1 where temp1.counts_total<10 group by temp1.typeId"
    // //Slie 36:Share of facilities with less than 10 work orders in a year
    // public static final String SHARE_FAC_WORK_ORDER_LESS_SIMPLE_SLD36="select count(temp1.counts_total)/(select count(loc.id) as total_fac FROM chai_location_data_location loc where loc.id @DATA_LOCATION) as final_result from (SELECT count(equ.data_location_id) as counts_total FROM memms_equipment equ,memms_work_order wo  where equ.id=wo.equipment_id and  equ.data_location_id @DATA_LOCATION and dateDiff(NOW(),wo.open_on)<=365 group by equ.data_location_id) temp1 where temp1.counts_total<10"
    // public static final String SHARE_FAC_WORK_ORDER_LESS_GROUP_SLD36="select temp1.typeName,count(temp1.counts_total)/(select count(loc.id) as total_fac FROM chai_location_data_location loc where loc.id @DATA_LOCATION) as final_result from (SELECT count(equ.data_location_id) as counts_total,locTy.id as typeid,locTy.names_en as typeName FROM memms_equipment equ,chai_location_data_location locc,chai_location_data_location_type locTy,memms_work_order wo  where equ.data_location_id=locc.id and locc.type=locTy.id and equ.id=wo.equipment_id and  equ.data_location_id @DATA_LOCATION and dateDiff(NOW(),wo.open_on)<=365 group by equ.data_location_id) temp1 where temp1.counts_total<10 group by temp1.typeid"
    // //Slie 37:Average percentage of equipments per facility having a preventive maintenance plan
    // public static final String AVG_PERCENT_PREV_SIMPLE_SLD37="select count(provOrderTemp1.equipments)/(select  count(equ1.code) as total_equipents from memms_equipment equ1 where equ1.data_location_id @DATA_LOCATION) as final_result from (SELECT privOrder.equipment_id as equipments FROM memms_equipment equ,memms_preventive_order_abstract privOrder  where equ.id=privOrder.equipment_id and  equ.data_location_id @DATA_LOCATION group by privOrder.equipment_id) provOrderTemp1"
    // public static final String AVG_PERCENT_PREV_GROUP_SLD37="select provOrderTemp1.typeName,count(provOrderTemp1.equipments)/(select  count(equ1.code) as total_equipents from memms_equipment equ1 where equ1.data_location_id @DATA_LOCATION) as final_result from (SELECT privOrder.equipment_id as equipments,locty.id as typeId,locty.names_en as typeName FROM memms_equipment equ,chai_location_data_location loc,chai_location_data_location_type locty,memms_preventive_order_abstract privOrder  where equ.data_location_id=loc.id and loc.type=locty.id and equ.id=privOrder.equipment_id and  equ.data_location_id @DATA_LOCATION group by privOrder.equipment_id) provOrderTemp1 group by provOrderTemp1.typeId"
    // //Slie 38:Share of facilities with no preventive maintenance plan for more than 10%  of the equipment
    // public static final String SHARE_FAC_NO_PRIV_SIMPLE_SLD38="select count(tempp.nopreventions)/(select count(loc.id) as totalfac from chai_location_data_location loc where loc.id @DATA_LOCATION) as final_result from (SELECT count(equ.id)/(select  count(equ1.code) from memms_equipment equ1 where equ1.data_location_id @DATA_LOCATION ) as nopreventions FROM memms_equipment equ where equ.id not in(SELECT m.equipment_id FROM memms_preventive_order_abstract m) and equ.data_location_id @DATA_LOCATION  group by equ.data_location_id) tempp where tempp.nopreventions*100<=10"
    // public static final String SHARE_FAC_NO_PRIV_GROUP_SLD38="select tempp.typeName,count(tempp.nopreventions)/(select count(loc.id) as totalfac from chai_location_data_location loc where loc.id @DATA_LOCATION) as final_result from (SELECT count(equ.id)/(select  count(equ1.code) from memms_equipment equ1 where equ1.data_location_id @DATA_LOCATION ) as nopreventions,locTy.id as typeid,locTy.names_en as typeName FROM memms_equipment equ,chai_location_data_location locc,chai_location_data_location_type locTy where equ.data_location_id=locc.id and locc.type=locTy.id and equ.id not in(SELECT m.equipment_id FROM memms_preventive_order_abstract m) and equ.data_location_id @DATA_LOCATION  group by equ.data_location_id) tempp where tempp.nopreventions*100<=10  group by tempp.typeid"
    // //Slie 39:Share of warehouses with less than 20 spare part inventory status  changes in a month
    // public static final String SHARE_WHERE_LESS_20_SIMPLE_SLD39="select count(temp1.counts_total)/(select count(loc.id) as total_fac FROM chai_location_data_location loc  where loc.id @DATA_LOCATION) as final_result from (SELECT count(spst.spare_part_id) as counts_total FROM memms_spare_part sp,memms_spare_part_status spst  where sp.id=spst.spare_part_id and sp.data_location_id @DATA_LOCATION and dateDiff(NOW(),spst.date_of_event) <=30 group by sp.data_location_id) temp1 where temp1.counts_total<=20;"
    // public static final String SHARE_WHERE_LESS_20_GROUP_SLD39="select temp1.typeName,count(temp1.counts_total)/(select count(loc.id) as total_fac FROM chai_location_data_location loc  where loc.id @DATA_LOCATION) as final_result from (SELECT count(spst.spare_part_id) as counts_total,loctTy.id as typeid,loctTy.names_en as typeName FROM memms_spare_part sp,chai_location_data_location loct,chai_location_data_location_type loctTy,memms_spare_part_status spst  where sp.data_location_id=loct.id and loct.type=loctTy.id  and sp.id=spst.spare_part_id and sp.data_location_id @DATA_LOCATION and dateDiff(NOW(),spst.date_of_event) <=30 group by sp.data_location_id) temp1 where temp1.counts_total<=20 group by temp1.typeid;"

    public static createDashboardStructure() {
        createIndicatorCategories()
        createUserDefinedVariables()
        createIndicators()
    }

    
    public static createIndicators() {
        def equipementManagment = IndicatorCategory.findByCode(MANAGEMENT_EQUIPMENT)
        if(equipementManagment != null) {

            //Slid 7:Share of operational equipment
            newIndicator(
                equipementManagment,
                "SHARE_OPE_EQUIPMENT", 
                ["en":"Share of operational equipment","fr":"Share of operational equipment fr"],
                ["en":"Share of operational equipment","fr":"Share of operational equipment fr"],
                [
                    "en":"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance})",
                    "fr":"(total number equipment with STATUS=Operational) / by (total number equipment with STATUS={Operational Partially operational Under maintenance}) fr"
                ],
                "%",0.8,0.9,Indicator.HistoricalPeriod.QUARTERLY,8, 
                SHARE_OPERATIONAL_SIMPLE_SLD7, 
                ["en":"Type of Equipment","fr":"Type d'equipment fr"],
                SHARE_OPERATIONAL_GROUP_SLD7,
                false,true
            )

            // //Slie 12:Share of obsolete equipment
            newIndicator(
                equipementManagment, 
                "SHARE_OBSOLETE_EQUIPMENT", 
                ["en":"Share of obsolete equipment","fr":"Share of obsolete equipment fr"],
                ["en":"Share of obsolete equipment","fr":"Share of obsolete equipment fr"],
                [
                    "en":"(total number equipment with STATUS={Operational; Partially operational, Under maintenance} and OBSELETE BOX CHECKED)/(total number equipment with  STATUS = {Operational; Partially operational, Under maintenance})",
                    "fr":"(total number equipment with STATUS={Operational; Partially operational, Under maintenance} and OBSELETE BOX CHECKED)/(total number equipment with  STATUS = {Operational; Partially operational, Under maintenance}) fr"
                ],
                "%",0.30,0.10,Indicator.HistoricalPeriod.MONTHLY,5, 
                SHARE_OBSOLETE_SIMPLE_SLD12, 
                ["en":"Type of Equipment","fr":"Type d'equipment fr"],
                SHARE_OBSOLETE_GROUP_SLD12,
                false,true
            )
            // //Slie 8:Share of equipment for disposal
            // new Indicator(category:equipementManagment, code:"SHARE_DISPOSAL_MANAGEMENT", name_en:"Share of equipment for disposal",name_fr:"Share of equipment for disposal",description_en:"Share of equipment for disposal",description_fr:"Share of equipment for disposal", formula_en:"total number equipment with STATUS=For disposal)/ (total number equipment with STATUS={Operational; Partially operational Under maintenanceFor disposal})", formula_fr:"total number equipment with STATUS=For disposal)/ (total number equipment with STATUS={Operational; Partially operational Under maintenanceFor disposal})",unit:"%",redToYellowThreshold:0.06,yellowToGreenThreshold:0.09, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_DISPOSAL_SIMPLE_SLD8, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:SHARE_DISPOSAL_GROUP_SLD8,sqlQuery:false, active:true).save(failOnError: true, flush:true)
            // //Slie 9:Share of equipment in stock
            // new Indicator(category:equipementManagment, code:"SHARE_STOCK_EQUIPMENT", name_en:"Share of equipment in stock",name_fr:"Share of equipment in stock",description_en:"Share of equipment in stock",description_fr:"Share of equipment in stock", formula_en:"total number  of equipment with STATUS=In stock)/ (total number equipment with STATUS={In stock, Operational; Partially operational, Under maintenance,For disposal})", formula_fr:"total number  of equipment with STATUS=In stock)/ (total number equipment with STATUS={In stock, Operational; Partially operational, Under maintenance,For disposal})",unit:"%",redToYellowThreshold:0.06,yellowToGreenThreshold:0.04, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_STOCK_SIMPLE_SLD9, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:SHARE_STOCK_GROUP_SLD9,sqlQuery:false, active:true).save(failOnError: true, flush:true)
            // //Slie 10:Degree of equipment standardization
            // new Indicator(category:equipementManagment, code:"DEGREE_STD_EQUIPMENT", name_en:"Degree of equipment standardization",name_fr:"Degree of equipment standardization",description_en:"Degree of equipment standardization",description_fr:"Degree of equipment standardization", formula_en:"(max (no. of equipments of type 1 from one manufacturer)+max(no. of equipments of type 2 from one manufacturer)+....) / (Total no. of equipments at the facility except equipments with status = For disposal or Disposed))", formula_fr:"(max (no. of equipments of type 1 from one manufacturer)+max(no. of equipments of type 2 from one manufacturer)+....) / (Total no. of equipments at the facility except equipments with status = For disposal or Disposed))",unit:"%",redToYellowThreshold:0.6,yellowToGreenThreshold:0.8, historicalPeriod:Indicator.HistoricalPeriod.YEARLY, historyItems:5, queryScript:DEGREE_STANDARDIZATION_SIMPLE_SLD10, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:DEGREE_STANDARDIZATION_GROUP_SLD10,sqlQuery:true, active:true).save(failOnError: true, flush:true)
            // //Slie 11:Share of equipment donated
            // new Indicator(category:equipementManagment, code:"SHARE_DONATED_EQUIPMENT", name_en:"Share of equipments donated",name_fr:"Share of equipments donated",description_en:"Share of equipments donated",description_fr:"Share of equipments donated", formula_en:"(total number equipment with STATUS={Operational; Partially operational, Under maintenance} and PURCHASER = {Donor})/(total number equipment with STATUS={Operational; Partially operational, Under maintenance})", formula_fr:"(total number equipment with STATUS={Operational; Partially operational, Under maintenance} and PURCHASER = {Donor})/(total number equipment with STATUS={Operational; Partially operational, Under maintenance})",unit:"%",redToYellowThreshold:0.3,yellowToGreenThreshold:0.10, historicalPeriod:Indicator.HistoricalPeriod.YEARLY, historyItems:5, queryScript:SHARE_DONATED_SIMPLE_SLD11, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:SHARE_DONATED_GROUP_SLD11,sqlQuery:false, active:true).save(failOnError: true, flush:true)
           

            // //Slie 13:Share of equipment under active warranty or an active service provider contract
            // new Indicator(category:equipementManagment, code:"SHARE_ACTIVE_WARR_CONTR_EQUIPMENT", name_en:"Share of equipment under active warranty or an active service provider  contract",name_fr:"Share of equipment under active warranty or an active service provider  contract",description_en:"Share of equipment under active warranty or an active service provider  contract",description_fr:"Share of equipment under active warranty or an active service provider  contract", formula_en:"[(total number equipment with STATUS={Operational; Partially operational, Under maintenance} and [{Current Date – Warranty Start Date}", formula_fr:"[(total number equipment with STATUS={Operational; Partially operational, Under maintenance} and [{Current Date – Warranty Start Date}",unit:"%",redToYellowThreshold:0.30,yellowToGreenThreshold:0.80, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_ACTIVE_WARANTY_SIMPLE_SLD13, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:SHARE_ACTIVE_WARANTY_GROUP_SLD13,sqlQuery:true, active:true).save(failOnError: true, flush:true)
            // //Slie 14:Share of equipment with lifetime expiring in less than 2 years
            // new Indicator(category:equipementManagment, code:"SHARE_LIFETIME_EXPIRING_IN2Y_EQUIPEMENT", name_en:"Share of equipment with lifetime expiring in less than 2 years",name_fr:"Share of equipment with lifetime expiring in less than 2 years",description_en:"Share of equipment with lifetime expiring in less than 2 years",description_fr:"Share of equipment with lifetime expiring in less than 2 years", formula_en:"(total number equipment with STATUS={“Operational”; “Partially operational”, “Under maintenance”} and {Current Date – date of first inventory updationor DATE OF PURCHASE} >{Expected life time – 2years)/", formula_fr:"(total number equipment with STATUS={“Operational”; “Partially operational”, “Under maintenance”} and {Current Date – date of first inventory updationor DATE OF PURCHASE} >{Expected life time – 2years)/",unit:"%",redToYellowThreshold:0.30,yellowToGreenThreshold:0.10, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_LIFETIME_LESS_SIMPLE_SLD14, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:SHARE_LIFETIME_LESS_GROUP_SLD14,sqlQuery:false, active:true).save(failOnError: true, flush:true)
        }
        def correctiveMaintenance = IndicatorCategory.findByCode(CORRECTIVE_MAINTENANCE)
        if(correctiveMaintenance != null) {
     //        //Slie 15:Share of equipments for which a work order was generated
     //        new Indicator(category:correctiveMaintenance, code:"SHARE_WORK_ORDER_CORR_MAINTENANCE", name_en:"Share of equipments for which a work order was generated",name_fr:"Share of equipments for which a work order was generated",description_en:"Share of equipments for which a work order was generated",description_fr:"Share of equipments for which a work order was generated", formula_en:"Total number of equipments for which work order was generated / total number of equipment with status ={ “Operational”, “Partially operational”,”Under maintenance”}", formula_fr:"Total number of equipments for which work order was generated / total number of equipment with status ={ “Operational”, “Partially operational”,”Under maintenance”}",unit:"%",redToYellowThreshold:0.15,yellowToGreenThreshold:0.20, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_WORK_ORDER_GEN_SIMPLE_SLD15, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:SHARE_WORK_ORDER_GEN_GROUP_SLD15,sqlQuery:false, active:true).save(failOnError: true, flush:true)
            //Slie 16:Degree of corrective maintenance execution according to benchmark
            newIndicator(
                correctiveMaintenance, 
                "DEGREE_CORR_EXEC_BENCHNARK_MAINTENANCE", 
                ["en":"Degree of corrective maintenance execution according to benchmark","fr":"Degree of corrective maintenance execution according to benchmark fr"],
                ["en":"Degree of corrective maintenance execution according to benchmark","fr":"Degree of corrective maintenance execution according to benchmark fr"], 
                [
                    "en":"Total no. of work orders with status changed from “open at facility” or “open at MM” to “Closed fixed”/ total no. of work orders generated in a given time frame (time frame to be decided by the user)", 
                    "fr":"Total no. of work orders with status changed from “open at facility” or “open at MM” to “Closed fixed”/ total no. of work orders generated in a given time frame (time frame to be decided by the user) fr"
                ],
                "%", 0.85, 0.95, Indicator.HistoricalPeriod.QUARTERLY, 8, 
                DEGREE_CORRECTIVE_EX_SIMPLE_SLD16, 
                ["en":"Type of Equipment","fr":"Type of Equipment fr"], 
                DEGREE_CORRECTIVE_EX_GROUP_SLD16,
                false, true
            )
     //        //Slie 17:Share of work orders witnessing re-incidence in a period of time (user defined)
     //        new Indicator(category:correctiveMaintenance, code:"SHARE_REINCIDENCE_CORR_MAINTENANCE", name_en:"Share of work orders witnessing re-incidence in a period of time (user defined)",name_fr:"Share of work orders witnessing re-incidence in a period of time (user defined)",description_en:"Share of work orders witnessing re-incidence in a period of time (user defined)",description_fr:"Share of work orders witnessing re-incidence in a period of time (user defined)", formula_en:"Total no. of work orders witnessing re-incidence in a given time frame/total no. Of work orders in that time frame", formula_fr:"Total no. of work orders witnessing re-incidence in a given time frame/total no. Of work orders in that time frame",unit:"%",redToYellowThreshold:0.01,yellowToGreenThreshold:0.05, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_WORK_ORDER_WITNESSING_SIMPLE_SLD17, groupName_en:"Service Provider", groupName_fr:"Service Provider", groupQueryScript:SHARE_WORK_ORDER_WITNESSING_GROUP_SLD17,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //        //Slie 18:Cost effectiveness of corrective maintenance
     //        new Indicator(category:correctiveMaintenance, code:"COST_EFFECTIVENESS_CORR_MAINTENANCE", name_en:"Cost effectiveness of corrective maintenance",name_fr:"Cost effectiveness of corrective maintenance",description_en:"Cost effectiveness of corrective maintenance",description_fr:"Cost effectiveness of corrective maintenance", formula_en:"Average equipments (cumulative cost of corrective maintenance (cost of all work orders in equipment’s history) for an equipment / cost of the equipment)", formula_fr:"Average equipments (cumulative cost of corrective maintenance (cost of all work orders in equipment’s history) for an equipment / cost of the equipment)",unit:"%",redToYellowThreshold:0.4,yellowToGreenThreshold:0.2, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:COST_EFF_CORR_SIMPLE_SLD18, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:COST_EFF_CORR_GROUP_SLD18,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //        //Slie 19:Share of work orders escalated to MMC
     //        new Indicator(category:correctiveMaintenance, code:"SHARE_ESCLATED_TO_MMC_MAINTENANCE", name_en:"Share of work orders escalated to MMC",name_fr:"Share of work orders escalated to MMC",description_en:"Share of work orders escalated to MMC",description_fr:"Share of work orders escalated to MMC", formula_en:"Total work orders with status change from “open at facility” to :open at MMC” / total work orders", formula_fr:"Total work orders with status change from “open at facility” to :open at MMC” / total work orders",unit:"%",redToYellowThreshold:0.3,yellowToGreenThreshold:0.1, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_ESCLATED_MMC_SIMPLE_SLD19, groupName_en:"Type of Equipment", groupName_fr:"same", groupQueryScript:SHARE_ESCLATED_MMC_GROUP_SLD19,sqlQuery:false, active:true).save(failOnError: true, flush:true)
     //        //Slie 20:Average time to fix equipments
     //        new Indicator(category:correctiveMaintenance, code:"AVERAGE_TIME_TO_FIX_EQUI_MAINTENAMCE", name_en:"Average time to fix equipments",name_fr:"Average time to fix equipments",description_en:"Average time to fix equipments",description_fr:"Average time to fix equipments", formula_en:"Average time to fix equipments", formula_fr:"Average time to fix equipments",unit:"day(s)",redToYellowThreshold:3,yellowToGreenThreshold:1, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:AVGE_FIX_SIMPLE_SLD20, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:AVGE_FIX_GROUP_SLD20,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //        //Slie 21:Average time to fix equipments by the District Hospital technician
     //        new Indicator(category:correctiveMaintenance, code:"AVERAGE_TIME_FIX_DH_TECH_MAINTENANCE", name_en:"Average time to fix equipments by the District Hospital technician",name_fr:"Average time to fix equipments by the District Hospital technician",description_en:"Average time to fix equipments by the District Hospital technician",description_fr:"Average time to fix equipments by the District Hospital technician", formula_en:"Average time taken for those work orders to close which were never escalated to the MMC, i.e. Status change from “open at facility” to “closed fixed” without there being an intermediate “open at MMC”", formula_fr:"Average time taken for those work orders to close which were never escalated to the MMC, i.e. Status change from “open at facility” to “closed fixed” without there being an intermediate “open at MMC”",unit:"day(s)",redToYellowThreshold:3.0,yellowToGreenThreshold:1.0, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:AVGE_TIME_FIX_DH_SIMPLE_SLD21, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:AVGE_TIME_FIX_DH_GROUP_SLD21,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //        //Slie 22:Average time to fix equipments by MMC
     //        new Indicator(category:correctiveMaintenance, code:"AVERAGE_TIME_FIX_MMC_TECH_MAINTENANCE", name_en:"Average time to fix equipments by MMC",name_fr:"Average time to fix equipments by MMC",description_en:"Average time to fix equipments by MMC",description_fr:"Average time to fix equipments by MMC", formula_en:"Average time for work orders to change status from “open at MMC” to “closed fixed”", formula_fr:"Average time for work orders to change status from “open at MMC” to “closed fixed”",unit:"day(s)",redToYellowThreshold:3.0,yellowToGreenThreshold:1.0, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:AVGE_TIME_FIX_MMC_SIMPLE_SLD22, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:AVGE_TIME_FIX_MMC_GROUP_SLD22,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //        //Slie 23:Share of work orders requiring new spare parts (not yet)
	    // // def indicator17=new Indicator(category:equipementManagment, code:"", name_en:"",name_fr:"same",description_en:"",description_fr:"same", formula_en:"", formula_fr:"",unit:"%",redToYellowThreshold:10.0,yellowToGreenThreshold:10.0, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_WORK_ORDER_REQ_SP_SIMPLE_SLD23, groupName_en:"", groupName_fr:"same", groupQueryScript:SHARE_WORK_ORDER_REQ_SP_GROUP_SLD23,sqlQuery:false, active:false).save(failOnError: true, flush:true)
     //        //Slie 24:Share of problems caused by misuse
     //        new Indicator(category:correctiveMaintenance, code:"SHARE_PROBLEMS_MISIUSE_MAINTENANCE", name_en:"Share of problems caused by misuse",name_fr:"Share of problems caused by misuse",description_en:"Share of problems caused by misuse",description_fr:"Share of problems caused by misuse", formula_en:"number of work orders with “misuse” as reason of equipment failure / total number of work orders generated", formula_fr:"number of work orders with “misuse” as reason of equipment failure / total number of work orders generated",unit:"%",redToYellowThreshold:0.30,yellowToGreenThreshold:0.30, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_PROBLEM_MISUSE_SIMPLE_SLD24, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:SHARE_PROBLEM_MISUSE_GROUP_SLD24,sqlQuery:false, active:true).save(failOnError: true, flush:true)
        }
    def priventiveMaintenance = IndicatorCategory.findByCode(PRIVENTIVE_MAINTENANCE)
        if(priventiveMaintenance != null) {
            // //Slie 26:Degree of execution of preventive maintenance
            newIndicator(
                priventiveMaintenance, 
                "DEGREE_EXECUTION_PREV_MAINTENANCE", 
                ["en":"Degree of execution of preventive maintenance","fr":"Degree of execution of preventive maintenance fr"],
                ["en":"Degree of execution of preventive maintenance","fr":"Degree of execution of preventive maintenance fr"], 
                [
                    "en":"Number of preventive maintenance deadlines met / total  number of preventive maintenance deadlines",
                    "fr":"Number of preventive maintenance deadlines met / total  number of preventive maintenance deadlines fr"
                ],
                "%",0.80,0.90, Indicator.HistoricalPeriod.QUARTERLY,8,
                DEGREE_EXCUTION_PREV_SIMPLE_SLD26,
                ["en":"Type of Equipment","fr":"Type of Equipment fr"],
                DEGREE_EXCUTION_PREV_GROUP_SLD26,
                false,true
            )
            // //Slie 27:Share of maintenance executed at the facility
            // new Indicator(category:priventiveMaintenance, code:"SHARE_FACILITY_MAINTENANCE", name_en:"Share of maintenance executed at the facility",name_fr:"Share of maintenance executed at the facility",description_en:"Share of maintenance executed at the facility",description_fr:"Share of maintenance executed at the facility", formula_en:"Number of PM deadlines adhered to at the facility /Total number of PM deadlines", formula_fr:"Number of PM deadlines adhered to at the facility /Total number of PM deadlines",unit:"%",redToYellowThreshold:0.80,yellowToGreenThreshold:0.90, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_MAIN_EXC_SIMPLE_SLD27, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:SHARE_MAIN_EXC_GROUP_SLD27,sqlQuery:false, active:true).save(failOnError: false, flush:true)
            // //Slie 28:Deviation against scheduled date
            // new Indicator(category:priventiveMaintenance, code:"DEVIATN_AGNST_SCHDLD_DATE_MAINTENANCE", name_en:"Deviation against scheduled date",name_fr:"Deviation against scheduled date",description_en:"Deviation against scheduled date",description_fr:"Deviation against scheduled date", formula_en:"Average difference between the time when the deadline was supposed to be met and the time when the deadline was actually met", formula_fr:"Average difference between the time when the deadline was supposed to be met and the time when the deadline was actually met",unit:"day(s)",redToYellowThreshold:2,yellowToGreenThreshold:1, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:DEVIATION_AGAIST_SIMPLE_SLD28, groupName_en:"Type of Equipment", groupName_fr:"Type of Equipment", groupQueryScript:DEVIATION_AGAIST_GROUP_SLD28,sqlQuery:false, active:true).save(failOnError: true, flush:true)
        }
        // def sparePartsManagment = IndicatorCategory.findByCode(MANAGEMENT_SPARE_PARTS)
        // if(sparePartsManagment != null) {
        //     //Slie 29:Number of types of spare parts about to be discontinued in a year
        //     new Indicator(category:sparePartsManagment, code:"NUMBER_DISCONTINUED_YEAR_MAN_SPARE_PART", name_en:"Number of types of spare parts about to be discontinued in a year",name_fr:"Number of types of spare parts about to be discontinued in a year",description_en:"Number of types of spare parts about to be discontinued in a year",description_fr:"Number of types of spare parts about to be discontinued in a year", formula_en:"Total number of types of spare parts with their discontinuation date within a year;", formula_fr:"Total number of types of spare parts with their discontinuation date within a year;",unit:"Spare Part Type(s)",redToYellowThreshold:20,yellowToGreenThreshold:10, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:NUMBER_TYPE_SPARE_DISC_SIMPLE_SLD29, groupName_en:"Spare Part Type", groupName_fr:"Spare Part Type", groupQueryScript:NUMBER_TYPE_SPARE_DISC_GROUP_SLD29,sqlQuery:false, active:true).save(failOnError: true, flush:true)
        //     //Slie 30:Share of types of spare parts about to stock out in a given time period
        //     new Indicator(category:sparePartsManagment, code:"SHARE_TYPES_SP_PRT_STOC_OUT_LESS", name_en:"Share of types of spare parts about to stock out in a given time period",name_fr:"Share of types of spare parts about to stock out in a given time period",description_en:"Share of types of spare parts about to stock out in a given time period",description_fr:"Share of types of spare parts about to stock out in a given time period", formula_en:"No. Of spare part types at a facility with stock out time less than a certain threshold (to be defined by the administrator) / total number of spare part types at the facility", formula_fr:"No. Of spare part types at a facility with stock out time less than a certain threshold (to be defined by the administrator) / total number of spare part types at the facility",unit:"%",redToYellowThreshold:0.20,yellowToGreenThreshold:0.10, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_TYPE_SP_PART_STOCK_OUT_SIMPLE_SLD30, groupName_en:"Spare Part", groupName_fr:"Spare Part", groupQueryScript:SHARE_TYPE_SP_PART_STOCK_OUT_GROUP_SLD30,sqlQuery:true, active:true).save(failOnError: true, flush:true)
        //     //Slie 31:Number of types of spare parts with stock more than a given time period
        //     new Indicator(category:sparePartsManagment, code:"NUMBER_TYPES_STC_OUNT_MORE_SPAREPART", name_en:"Number of types of spare parts with stock more than a given time period",name_fr:"Number of types of spare parts with stock more than a given time period",description_en:"Number of types of spare parts with stock more than a given time period",description_fr:"Number of types of spare parts with stock more than a given time period", formula_en:"No. Of spare part types at a facility with stock out time more than a certain threshold (to be defined by the administrator) / total number of spare part types at the facility", formula_fr:"No. Of spare part types at a facility with stock out time more than a certain threshold (to be defined by the administrator) / total number of spare part types at the facility",unit:"%",redToYellowThreshold:0.20,yellowToGreenThreshold:0.10, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:NUMBER_SP_PAT_STOCK_MORE_SIMPLE_SLD31, groupName_en:"Spare Part Type", groupName_fr:"Spare Part Type", groupQueryScript:NUMBER_SP_PAT_STOCK_MORE_GROUP_SLD31,sqlQuery:true, active:true).save(failOnError: true, flush:true)
        //     //Slie 32:Average stock time
        //     new Indicator(category:sparePartsManagment, code:"AVERAGE_STOCK_TIME_SPAREPART", name_en:"Average stock time",name_fr:"Average stock time",description_en:"Average stock time",description_fr:"Average stock time", formula_en:"(Number of spare parts of type 1 x use rate of spare part of type 1+number of spare part of type 2 x use rate of spare part of type 2.....)  /  (Total number of spare parts of all types)", formula_fr:"(Number of spare parts of type 1 x use rate of spare part of type 1+number of spare part of type 2 x use rate of spare part of type 2.....)  /  (Total number of spare parts of all types)",unit:"month(s)",redToYellowThreshold:6.0,yellowToGreenThreshold:2.0, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:AVG_STOCK_TIME_SIMPLE_SLD32, groupName_en:"Spare Part Type", groupName_fr:"Spare Part Type", groupQueryScript:AVG_STOCK_TIME_GROUP_SLD32,sqlQuery:true, active:true).save(failOnError: true, flush:true)
        //     //Slie 33:Average time between request and arrival of spare parts
        //     new Indicator(category:sparePartsManagment, code:"AVERAGE_WAITING_TIME_SPAREPART", name_en:"Average time between request and arrival of spare parts",name_fr:"Average time between request and arrival of spare parts",description_en:"Average time between request and arrival of spare parts",description_fr:"Average time between request and arrival of spare parts", formula_en:"Average time for status change from “pending order” to “in stock”", formula_fr:"Average time for status change from “pending order” to “in stock”",unit:"day(s)",redToYellowThreshold:3.0,yellowToGreenThreshold:1.0, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:AVG_REQUEST_ARVAL_SIMPLE_SLD33, groupName_en:"Spare Part Type", groupName_fr:"Spare Part Type", groupQueryScript:AVG_REQUEST_ARVAL_GROUP_SLD33,sqlQuery:false, active:true).save(failOnError: true, flush:true)
        // }
     //    def memmsUseMonitoring = IndicatorCategory.findByCode(MONITORING_MEMMS_USE)
     //    if(memmsUseMonitoring != null) {
     //        //Slie 34:Average number of equipments per facility(nee to be reviwed)
     //        new Indicator(category:memmsUseMonitoring, code:"AVERAGE_EQUI_PER_FACILITY_MEMMS_USE", name_en:"Average number of equipments per facility",name_fr:"Average number of equipments per facility",description_en:"Average number of equipments per facility",description_fr:"Average number of equipments per facility", formula_en:"Average number of equipments per facility – District hospital and health centre; different criteria to be defined for the two types of facilities", formula_fr:"Average number of equipments per facility – District hospital and health centre; different criteria to be defined for the two types of facilities",unit:"equipment(s)",redToYellowThreshold:80.0,yellowToGreenThreshold:100.0, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:AVG_NUM_EQU_FAC_SIMPLE_SLD34, groupName_en:"Facility", groupName_fr:"Facility", groupQueryScript:AVG_NUM_EQU_FAC_GROUP_SLD34,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //        //Slie 35:Share of facilities with less than 10 inventory status changes in a year
     //        new Indicator(category:memmsUseMonitoring, code:"SHARE_NV_STATUS_CHANGE_MEMMS_USE", name_en:"Share of facilities with less than 10 inventory status changes in a year",name_fr:"Share of facilities with less than 10 inventory status changes in a year",description_en:"Share of facilities with less than 10 inventory status changes in a year",description_fr:"Share of facilities with less than 10 inventory status changes in a year", formula_en:"Number of facilities with less than 10 inventory status changes / total number of facilities", formula_fr:"Number of facilities with less than 10 inventory status changes / total number of facilities",unit:"%",redToYellowThreshold:0.30,yellowToGreenThreshold:0.10, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_FAC_INVTORY_LESS_SIMPLE_SLD35, groupName_en:"Facility Type", groupName_fr:"Facility Type", groupQueryScript:SHARE_FAC_INVTORY_LESS_GROUP_SLD35,sqlQuery:true, active:true).save(failOnError: true, flush:true)
	    // //Slie 36:Share of facilities with less than 10 work orders in a year
     //        new Indicator(category:memmsUseMonitoring, code:"SHARE_FACILITY_WORK_ORDE_LESS10_MEMMS_USE", name_en:"Share of facilities with less than 10 work orders in a year",name_fr:"Share of facilities with less than 10 work orders in a year",description_en:"Share of facilities with less than 10 work orders in a year",description_fr:"Share of facilities with less than 10 work orders in a year", formula_en:"Number of facilities with less than 10 work orders in a year / Total number of facilities", formula_fr:"Number of facilities with less than 10 work orders in a year / Total number of facilities",unit:"%",redToYellowThreshold:0.30,yellowToGreenThreshold:0.10, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_FAC_WORK_ORDER_LESS_SIMPLE_SLD36, groupName_en:"Facility Type", groupName_fr:"Facility Type", groupQueryScript:SHARE_FAC_WORK_ORDER_LESS_GROUP_SLD36,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //        //Slie 37:Average percentage of equipments per facility having a preventive maintenance plan
     //        new Indicator(category:memmsUseMonitoring, code:"AVERAGE_PERC_EQU_PRIVE_PLAN_MEMMS_USE", name_en:"Average percentage of equipments per facility having a preventive maintenance plan",name_fr:"Average percentage of equipments per facility having a preventive maintenance plan",description_en:"Average percentage of equipments per facility having a preventive maintenance plan",description_fr:"Average percentage of equipments per facility having a preventive maintenance plan", formula_en:"Average percentage of equipments per facility having a preventive maintenance plan", formula_fr:"Average percentage of equipments per facility having a preventive maintenance plan",unit:"%",redToYellowThreshold:0.80,yellowToGreenThreshold:0.90, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:AVG_PERCENT_PREV_SIMPLE_SLD37, groupName_en:"Facility Type", groupName_fr:"Facility Type", groupQueryScript:AVG_PERCENT_PREV_GROUP_SLD37,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //        //Slie 38:Share of facilities with no preventive maintenance plan for more than 10%  of the equipment
     //        new Indicator(category:memmsUseMonitoring, code:"SHARE_FACILITY_PRIV_MAIN_MOR10_MEMMS_USE", name_en:"Share of facilities with no preventive maintenance plan for more than 10%  of the equipment",name_fr:"Share of facilities with no preventive maintenance plan for more than 10%  of the equipment",description_en:"Share of facilities with no preventive maintenance plan for more than 10%  of the equipment",description_fr:"Share of facilities with no preventive maintenance plan for more than 10%  of the equipment", formula_en:"Number of facilities with no preventive maintenance plan for more than 10 % of the equipments / Total no. Of facilities", formula_fr:"Number of facilities with no preventive maintenance plan for more than 10 % of the equipments / Total no. Of facilities",unit:"%",redToYellowThreshold:0.30,yellowToGreenThreshold:0.10, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_FAC_NO_PRIV_SIMPLE_SLD38, groupName_en:"Facility Type", groupName_fr:"Facility Type", groupQueryScript:SHARE_FAC_NO_PRIV_GROUP_SLD38,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //        //Slie 39:Share of warehouses with less than 20 spare part inventory status  changes in a month
	    // new Indicator(category:memmsUseMonitoring, code:"SHARE_WHARE_INVE_STATUS_CHANG_MEMMS_USE", name_en:"Share of warehouses with less than 20 spare part inventory status  changes in a month",name_fr:"Share of warehouses with less than 20 spare part inventory status  changes in a month",description_en:"Share of warehouses with less than 20 spare part inventory status  changes in a month",description_fr:"Share of warehouses with less than 20 spare part inventory status  changes in a month", formula_en:"Number of warehouses with less than 20 spare part inventory status changes in a month / Total no. Of warehouses", formula_fr:"Number of warehouses with less than 20 spare part inventory status changes in a month / Total no. Of warehouses",unit:"%",redToYellowThreshold:0.30,yellowToGreenThreshold:0.10, historicalPeriod:Indicator.HistoricalPeriod.QUARTERLY, historyItems:8, queryScript:SHARE_WHERE_LESS_20_SIMPLE_SLD39, groupName_en:"Facility Type", groupName_fr:"Facility Type", groupQueryScript:SHARE_WHERE_LESS_20_GROUP_SLD39,sqlQuery:true, active:true).save(failOnError: true, flush:true)
     //    }
    }
    public static createUserDefinedVariables() {
            newUserDefinedVariable(
                "WO_REINCIDENCE_DAYS",
                ["en":"Work order re-incidence period(days)","fr":"Work order re-incidence period(days) fr"], 
                365.0
            )
            newUserDefinedVariable(
                "DEGGREE_CORRE_MAINT_EXECUTION",
                ["en":"Degree of corrective maintenance execution time frame","fr":"Degree of corrective maintenance execution time frame fr"], 
                365.0
            )
            newUserDefinedVariable(
                "TRASHHOLD_MIN_STOCT_OUT",
                ["en":"Minimum Stock-Out time threshold(days)","fr":"Minimum Stock-Out time threshold(days) fr"], 
                365.0
            )
            newUserDefinedVariable(
                "TRASHHOLD_MAX_STOCT_OUT",
                ["en":"Maximum Stock-Out time threshold(days)","fr":"Maximum Stock-Out time threshold(days) fr"], 
                30.0
            )
    }
    public static createIndicatorCategories() {
        new IndicatorCategory(
            code:CORRECTIVE_MAINTENANCE,
            name_en:"Corrective maintenance",
            redToYellowThreshold:0.6,
            yellowToGreenThreshold:0.8
            ).save(failOnError: true, flush:true)
        new IndicatorCategory(
            code:PRIVENTIVE_MAINTENANCE,
            name_en:"Preventive maintenance",
            redToYellowThreshold:0.6,
            yellowToGreenThreshold:0.8
            ).save(failOnError: true, flush:true)
        new IndicatorCategory(
            code:MANAGEMENT_EQUIPMENT,
            name_en:"Management of medical equipment",
            redToYellowThreshold:0.6,
            yellowToGreenThreshold:0.8
            ).save(failOnError: true, flush:true)
        new IndicatorCategory(
            code:MANAGEMENT_SPARE_PARTS,
            name_en:"Management of spare parts",
            redToYellowThreshold:0.6,
            yellowToGreenThreshold:0.8
            ).save(failOnError: true, flush:true)
        new IndicatorCategory(
            code:MONITORING_MEMMS_USE,
            name_en:"Monitoring of MEMMS use",
            redToYellowThreshold:0.6,
            yellowToGreenThreshold:0.8
            ).save(failOnError: true, flush:true)
    }

    public static newIndicatorCategory(def code, def names, def redToYellowThreshold, def yellowToGreenThreshold){
            def category = new IndicatorCategory(
                code:code,
                redToYellowThreshold:redToYellowThreshold,
                yellowToGreenThreshold:yellowToGreenThreshold
            )
            Utils.setLocaleValueInMap(category,names,"Names")
            return category.save(failOnError: true, flush:true)
    }

    public static newUserDefinedVariable(def code,def names, def currentValue){
            def userDefinedVariable = new UserDefinedVariable(
                code:code,
                currentValue:currentValue
            )
            Utils.setLocaleValueInMap(userDefinedVariable,names,"Names")
            return userDefinedVariable.save(failOnError: true, flush:true)
    }
           
    public static newIndicator(def category, def code, def names, def descriptions, def formulas, def unit, def redToYellowThreshold, def yellowToGreenThreshold, def historicalPeriod, def historyItems, def queryScript, def groupNames, def groupQueryScript, def sqlQuery, def active){

            def indicator = new Indicator(
                category:category, 
                code:code,
                unit:unit,
                redToYellowThreshold:redToYellowThreshold,
                yellowToGreenThreshold:yellowToGreenThreshold, 
                historicalPeriod:historicalPeriod, 
                historyItems:historyItems, 
                queryScript:queryScript, 
                groupQueryScript:groupQueryScript,
                sqlQuery:sqlQuery, 
                active:active
            )
            Utils.setLocaleValueInMap(indicator,names,"Names")
            Utils.setLocaleValueInMap(indicator,descriptions,"Descriptions")
            Utils.setLocaleValueInMap(indicator,formulas,"Formulas")
            Utils.setLocaleValueInMap(indicator,groupNames,"GroupNames")
            return indicator.save(failOnError: true, flush:true)
    }

}