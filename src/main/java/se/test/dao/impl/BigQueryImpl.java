package se.test.dao.impl;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;

public class BigQueryImpl implements se.test.dao.BigQuery {

	BigQuery bigQuery;
	public BigQueryImpl()
	{
		this.bigQuery = BigQueryOptions.defaultInstance().service();
		
	}
	
	
}
