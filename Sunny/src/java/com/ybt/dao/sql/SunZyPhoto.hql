-- getPhotoList
	SELECT * FROM SunZyPhoto a where
	if (:city != NULL) 
	begin
		city = :city
	end
	else
	begin
		1 = 1
	end
	;	