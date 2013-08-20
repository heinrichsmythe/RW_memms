<%@ page import="org.chai.memms.util.Utils" %>
<%@ page import="org.chai.memms.util.Utils.ReportSubType" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.chai.memms.security.User" %>
<%@ page import="org.chai.memms.corrective.maintenance.WorkOrderStatus.OrderStatus" %>
<%@ page import="org.chai.memms.corrective.maintenance.WorkOrder" %>
<table class="items">
	<thead>
		<tr>			
			<g:if test="${reportTypeOptions.contains('location')}">
				<g:sortableColumn property="dataLocation"  title="${message(code: 'location.label')}" params="${params}" />
			</g:if>
			<g:if test="${reportTypeOptions.contains('code')}">
				<th><g:message code="default.equipment.code.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('serialNumber')}">
				<th><g:message code="default.equipment.serial.number.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('equipmentType')}">
				<th><g:message code="default.equipment.type.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('model')}">
				<g:sortableColumn property="model"  title="${message(code: 'equipment.model.label')}" params="${params}" />
			</g:if>
			<g:if test="${reportTypeOptions.contains('manufacturer')}">
				<g:sortableColumn property="manufacturer"  title="${message(code: 'provider.type.manufacturer')}" params="${params}" />
			</g:if>

			<g:if test="${reportTypeOptions.contains('statusChanges')}">
				<g:sortableColumn property="statusChanges"  title="Status Changes" params="${params}" />
			</g:if>

			<g:if test="${reportTypeOptions.contains('currentStatus')}">
				<g:sortableColumn property="currentStatus" title="${message(code: 'entity.status.label')}" params="${params}" />
			</g:if>
			<g:if test="${reportTypeOptions.contains('travelTime')}">
				<th><g:message code="listing.report.corrective.travel.time.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('workTime')}">
				<th><g:message code="listing.report.corrective.work.time.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('estimatedCost')}">
				<th><g:message code="listing.report.corrective.estimated.cost.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('departmentRequestedOrder')}">
				<th><g:message code="listing.report.corrective.department.order.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('reasonsEquipmentFailure')}">
				<th><g:message code="listing.report.corrective.failure.reason.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('listPerformedActions')}">
				<th><g:message code="listing.report.corrective.performed.actions.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('description')}">
				<th><g:message code="listing.report.corrective.problem.description.label"/></th>
			</g:if>
			<g:if test="${reportTypeOptions.contains('dateOfEvent')}">
				<th><g:message code="listing.report.corrective.event.date.label"/></th>
			</g:if>
		</tr>
	</thead>
	%{-- TODO AR switch from list of work orders to list of work order statuses --}%
	<tbody>
		<%--<g:each in="${entities}" status="i" var="order">  --%>
		<g:each in="${entities}" status="i" var="orderStatus">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<g:if test="${reportTypeOptions.contains('location')}">
					<td>${orderStatus.workOrder.equipment.dataLocation.names}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('code')}">
					<td>${orderStatus.workOrder?.equipment?.code}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('serialNumber')}">
					<td>${orderStatus.workOrder.equipment.serialNumber}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('equipmentType')}">
					<td>${orderStatus.workOrder.equipment.type.names}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('model')}">
					<td>${orderStatus.workOrder.equipment.model}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('manufacturer')}">
					<td>${orderStatus.workOrder.equipment.manufacturer?.contact?.contactName}</td>
				</g:if>

				<g:if test="${reportTypeOptions.contains('statusChanges')}">
					<g:set var="statusChangesEnum" value="${orderStatus.getWorkOrderStatusChange(customizedListingParams?.statusChanges)}"/>
					<td>${message(code: statusChangesEnum?.messageCode+'.'+statusChangesEnum?.name)}</td>
				</g:if>

				<g:if test="${reportTypeOptions.contains('currentStatus')}">
					<td>${message(code: orderStatus.workOrder.currentStatus?.messageCode+'.'+orderStatus.workOrder.currentStatus?.name)}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('travelTime')}">
					<td>${orderStatus.workOrder.travelTime?.numberOfMinutes} Minutes</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('workTime')}">
					<td>${orderStatus.workOrder.workTime?.numberOfMinutes} Minutes</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('estimatedCost')}">
					<td>${orderStatus.workOrder.estimatedCost}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('departmentRequestedOrder')}">
					<td>${orderStatus.workOrder.equipment?.department?.code}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('reasonsEquipmentFailure')}">
					<td>${orderStatus.workOrder.failureReasonDetails}</td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('listPerformedActions')}">
					%{-- TODO AR corrective process property AFTER RELEASE --}%
					<td>${orderStatus.workOrder.actions}</td>
					
				</g:if>
				<g:if test="${reportTypeOptions.contains('description')}">
					<td><g:stripHtml field="${orderStatus.workOrder.description}" chars="30"/></td>
				</g:if>
				<g:if test="${reportTypeOptions.contains('dateOfEvent')}">
				<td>${orderStatus.workOrder.dateCreated}</td>
				</g:if>
			</tr>
		</g:each>

	</tbody>
</table>
<g:render template="/templates/pagination" />