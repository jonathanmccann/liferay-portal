<#assign entityColumns = entityFinder.entityColumns />

<#-- Case 3: entityFinder.isCollection() && !entityFinder.isUnique() -->

<#if entityFinder.isCollection() && !entityFinder.isUnique()>
	/**
	 * Removes all the ${entity.humanNames} where ${entityFinder.getHumanConditions(false)} from the database.
	 *
	<#list entityColumns as entityColumn>
	 * @param ${entityColumn.name} the ${entityColumn.humanName}
	</#list>
	 */
	@Override
	public void removeBy${entityFinder.name}(

	<#list entityColumns as entityColumn>
		${entityColumn.type} ${entityColumn.name}<#if entityColumn_has_next>,</#if>
	</#list>

	) {
		<#if entity.isBulkRemoveAvailable()>
			if (_isBulkRemovePossible()) {
				bulkRemoveBy${entityFinder.name}(
					<#list entityColumns as entityColumn>
						${entityColumn.name}<#if entityColumn_has_next>,</#if>
					</#list>
				);

				return;
			}
		</#if>

		for (${entity.name} ${entity.varName} : findBy${entityFinder.name}(

		<#list entityColumns as entityColumn>
			${entityColumn.name},
		</#list>

		QueryUtil.ALL_POS, QueryUtil.ALL_POS, null
		)) {
			remove(${entity.varName});
		}
	}

	<#if entity.isBulkRemoveAvailable()>
		protected void bulkRemoveBy${entityFinder.name}(

			<#list entityColumns as entityColumn>
				${entityColumn.type} ${entityColumn.name}<#if entityColumn_has_next>,</#if>
			</#list>

			) {

			StringBundler query = new StringBundler(${entityColumns?size + 1});

			<#if entity.hasManyToManyMappingColumn()>
				query.append(_SQL_SELECT_${entity.alias?upper_case}_PKS_WHERE);

				<#include "persistence_impl_finder_cols.ftl">

				String sql1 = query.toString();

				query.setStringAt(_SQL_DELETE_${entity.alias?upper_case}_WHERE, 0);

				String sql2 = query.toString();
			<#else>
				query.append(_SQL_DELETE_${entity.alias?upper_case}_WHERE);

				<#include "persistence_impl_finder_cols.ftl">

				String sql = query.toString();
			</#if>

			Session session = null;

			try {
				session = openSession();

				Query q = null;
				QueryPos qPos = null;

				<#if entity.hasManyToManyMappingColumn()>
					q = session.createQuery(sql1);

					qPos = QueryPos.getInstance(q);

					<@finderQPos />

					<#assign pkObjClassName = entity.getPKClassName() />

					<#if entity.hasPrimitivePK()>
						<#assign pkObjClassName = serviceBuilder.getPrimitiveObj(entity.getPKClassName()) />
					</#if>

					List<${pkObjClassName}> pks = (List<${pkObjClassName}>)QueryUtil.list(q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, false);

					for (${pkObjClassName} pk : pks) {
						<#list entity.entityColumns as entityColumn>
							<#if entityColumn.isCollection() && entityColumn.isMappingManyToMany()>
								<#assign referenceEntity = serviceBuilder.getEntity(entityColumn.entityName) />

								${entity.varName}To${referenceEntity.name}TableMapper.deleteLeftPrimaryKeyTableMappings(pk);
							</#if>
						</#list>
					}

					q = session.createQuery(sql2);
				<#else>
					q = session.createQuery(sql);
				</#if>

				qPos = QueryPos.getInstance(q);

				<@finderQPos />

				q.executeUpdate();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				closeSession(session);

				clearCache();
			}
		}
	</#if>
<#else>

<#-- Case 9: !entityFinder.isCollection() || entityFinder.isUnique() -->

	/**
	 * Removes the ${entity.humanName} where ${entityFinder.getHumanConditions(false)} from the database.
	 *
	<#list entityColumns as entityColumn>
	 * @param ${entityColumn.name} the ${entityColumn.humanName}
	</#list>
	 * @return the ${entity.humanName} that was removed
	 */
	@Override
	public ${entity.name} removeBy${entityFinder.name}(

	<#list entityColumns as entityColumn>
		${entityColumn.type} ${entityColumn.name}

		<#if entityColumn_has_next>
			,
		</#if>
	</#list>

	) throws ${noSuchEntity}Exception {
		${entity.name} ${entity.varName} = findBy${entityFinder.name}(

		<#list entityColumns as entityColumn>
			${entityColumn.name}

			<#if entityColumn_has_next>
				,
			</#if>
		</#list>

		);

		return remove(${entity.varName});
	}
</#if>