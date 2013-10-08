<div class="list-template">
	<%@ page import="org.chai.memms.spare.part.SparePart.StockLocation" %>
	<table class="items spaced">
		<thead>
			<tr>
				<th><g:message code="location.label"/></th>
				<th><g:message code="spare.part.type.label"/></th>
				<th><g:message code="entity.part.number.label"/></th>
				<th><g:message code="entity.code.label"/></th>
				<th><g:message code="spare.part.inititial.quantity"/></th>
				<th><g:message code="spare.part.in.stock.quantity"/></th>
				<th><g:message code="provider.type.manufacturer"/></th>
				<th><g:message code="spare.part.status.label"/></th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${entities}" status="i" var="sparePart">
				<tr >
					<td>
						<g:message code="spare.part.stockLocation.label"/>: ${message(code: sparePart.stockLocation?.messageCode+'.'+sparePart.stockLocation?.name)}<br/>
						<g:if test="${sparePart.stockLocation(StockLocation.FACILITY)}">
							<g:message code="dataLocation.label"/>: ${sparePart.dataLocation.names}<br/>
						</g:if>
						<g:message code="spare.part.room.label"/>: ${sparePart.room}<br/>
						<g:message code="spare.part.shelf.label"/>: ${sparePart.shelf}<br/>
					</td>
					<td>${sparePart.type.names}</td>
					<td>${sparePart.type.partNumber}</td>
					<td>${sparePart.type.code}</td>
					<td>${sparePart.initialQuantity}</td>
					<td>${sparePart.inStockQuantity}</td>
					<td>${sparePart.type.manufacturer?.contact?.contactName}</td>status
					<td>${message(code: sparePart.status?.messageCode+'.'+sparePart.status?.name)}</td>
				</tr>
			</g:each>
		</tbody>	
	</table>
	<g:render template="/templates/pagination" model="[entities:entities, entityCount:entities.totalCount]" />
</div>