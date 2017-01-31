package com.liferay.gsportal.core.service.persistence;

import com.liferay.gsportal.core.model.Client;
import com.liferay.gsportal.core.service.ClientLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component(service = Indexer.class)
public class ClientIndexer extends BaseIndexer<Client> {

	public static final String CLASS_NAME = Client.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Client client) throws Exception {

		deleteDocument(client.getCompanyId(), client.getClientId());
	}

	@Override
	protected Document doGetDocument(Client client) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, client);

		document.addKeyword("companyId", client.getCompanyId());
		document.addKeyword("clientId", client.getClientId());

		document.addText("name", client.getName());
		document.addText("description", client.getDescription());
		document.addText("dashboardURL", client.getDashboardUrl());

		if (_log.isDebugEnabled()) { _log.debug(
			String.format(
				"Index document was built for Client from organization '%s'",
				client.getName())); }

		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		// TODO implement logic to summarize results
		return null;
	}

	@Override
	protected void doReindex(Client client) throws Exception {
		Document document = getDocument(client);

		indexWriterHelper.updateDocument(
			getSearchEngineId(), client.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Client client = clientLocalService.getClient(classPK);

		doReindex(client);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		_reindexAllClients(companyId);
	}

	/**
	 * Reindexes all Clients in given company.
	 *
	 * @param companyId
	 * @throws Exception
	 */
	private void _reindexAllClients(long companyId) throws Exception {

		int count = clientLocalService.getClientsCount();

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexClients(companyId, start, end);
		}
	}

	protected void reindexClients(long companyId, int start, int end)
			throws Exception {

		List<Client> clients = clientLocalService.getClients(start, end);

		if (clients.isEmpty()) {
			return;
		}

		List<Document> documents = new ArrayList<Document>();

		for (Client client : clients) {
			Document document = getDocument(client);
			documents.add(document);
		}

		indexWriterHelper.updateDocuments(
			getSearchEngineId(), companyId, documents,
			isCommitImmediately());
	}

	@Reference
	protected ClientLocalService clientLocalService;

	@Reference
	protected IndexWriterHelper indexWriterHelper;

	private static Log _log = LogFactoryUtil.getLog(ClientIndexer.class);
}