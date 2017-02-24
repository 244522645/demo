-- getSatisticSqlList
	SELECT sun_gl_statistic_sql.type type,sun_gl_statistic_sql.title FROM sun_gl_statistic_sql  ORDER BY sql_code;
-- getSatisticSqlListTest
	SELECT * FROM sun_gl_statistic_sql  ORDER BY :orderBy;	