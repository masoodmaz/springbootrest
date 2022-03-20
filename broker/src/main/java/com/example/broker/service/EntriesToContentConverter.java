package com.example.broker.service;

import com.example.broker.model.Content;
import com.example.broker.model.Document;
import com.example.broker.model.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EntriesToContentConverter {

    private static final Logger logger = LoggerFactory.getLogger(EntriesToContentConverter.class);


    private static final String ENTRIES_KEY="entries";
    private static final String CONTENT_KEY="content";
    private static final String PROPERTIES_KEY="properties";
    private static final String DOC_NUMBER_KEY="document_number";
    private static final String DOC_REVISION_KEY="document_revision";
    private static final String URL_KEY="url";
    private static final String AF_ORGANISATION="AF";
    private static final String CF_PROPOSAL_TYPE="CF";
    private static final String YES_ASSERTION="Yes";
    private static final String JSON_PATH_EXCEPTION_MESSAGE="Unable to find field {} in the downstream-service response";
    private static final String JSON_PATH_RESPONSE_MESSAGE="Unable to find field %s in the downstream-service response";
    private static final String SKIP_DETAILS="Skipping entries due to missing key : {} , record number {} ";
    private static final String BUSINESS_KEY="ProposalType/Organisation";

    private  final ObjectMapper mapper;

    public EntriesToContentConverter(@Qualifier("customObjectMapper") ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String convert(String entriesJson) throws JsonProcessingException {
        JSONObject input=new JSONObject(entriesJson);
        JSONArray entries = input.getJSONArray(ENTRIES_KEY);

        if(null==entries)
        {
            logger.error(JSON_PATH_EXCEPTION_MESSAGE,ENTRIES_KEY);
            return String.format(JSON_PATH_RESPONSE_MESSAGE,ENTRIES_KEY);
        }
        List<Task> taskList=new ArrayList<>();
        for(int i=0;i<entries.length();i++)
        {
            JSONObject entry=entries.getJSONObject(i);
            JSONObject content=entry.getJSONObject(CONTENT_KEY);
            if(null==content)
            {
                logger.error(JSON_PATH_EXCEPTION_MESSAGE,CONTENT_KEY);
                logger.info(SKIP_DETAILS,CONTENT_KEY,i);
                continue;
            }
            JSONObject properties=content.getJSONObject(PROPERTIES_KEY) ;
            if(null==properties)
            {
                logger.error(JSON_PATH_EXCEPTION_MESSAGE,PROPERTIES_KEY);
                logger.info(SKIP_DETAILS,PROPERTIES_KEY,i);
                continue;
            }
            Task task = mapper.readValue(properties.toString(), Task.class);

            if(null==task.getProposal_type()||task.getProposal_type().isEmpty()||null==task.getOrganization()||task.getOrganization().isEmpty())
            {
                logger.error(JSON_PATH_EXCEPTION_MESSAGE,BUSINESS_KEY);
                logger.info(SKIP_DETAILS,BUSINESS_KEY,i);
                continue;
            }
            if(task.getOrganization().equals(AF_ORGANISATION) && task.getProposal_type().equals(CF_PROPOSAL_TYPE))
                task.setShowKitsToBeOrdered(YES_ASSERTION);
            List<Document> documentList=new ArrayList<>();
            Document document=new Document();
            document.setDocument_number((String)properties.get(DOC_NUMBER_KEY));
            document.setDocument_revision((String)properties.get(DOC_REVISION_KEY));
            document.setUrl((String)properties.get(URL_KEY));
            documentList.add(document);

            task.setDocumentList(documentList);
            taskList.add(task);
        }
        Content content=new Content(taskList);

       return mapper.writeValueAsString(content);
    }
}
