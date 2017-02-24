-- getMyList1
	SELECT a FROM SunDdOrder a where deleted=0 and sendeeId =:SENDEEID AND status != '10' order by createTime desc ;	
-- getMyList2
	SELECT a FROM SunDdOrder a where deleted=0 and buyerId =:BUYERID AND status != '10'  order by createTime desc ;	