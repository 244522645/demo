function showLocation(province , city , town) {
	
	var loc	= new Location();
	
	$("#loc_province,#loc_city,#loc_town").select2()
	$('#loc_province').change(function() {
		$('#loc_city').empty();
		//$('#loc_city').append(title[1]);
		//按需修正 
		var id=$('#loc_province').val().split('_')[1];
		loc.fillOption('loc_city' , '0,'+id);
		$('#loc_city').change()
	})
	
	$('#loc_city').change(function() {
		$('#loc_town').empty();
		//$('#loc_town').append(title[2]);
		//按需修正
		var cityid="";
		if($('#loc_city').val()!=null){
			cityid=$('#loc_city').val().split('_')[1];
		}else{
			$('#loc_city').append(title[1]);
			$('#loc_city').select2("val", "");
		}
		
		loc.fillOption('loc_town' , '0,' + $('#loc_province').val() + ',' + cityid);
	})
	
	/*$('#loc_town').change(function() {
		$('input[name=location_id]').val($(this).val());
	})*/
	
	if (province) {
		var id=province.split('_')[1];
		loc.fillOption('loc_province' , '0' , id);
		if (city) {
			//按需修正
			var cityid="";
			if(city!=null){
				cityid=city.split('_')[1];
			}else{
				$('#loc_city').append(title[1]);
			//	$('#loc_city').select2("val", "");
			}
			loc.fillOption('loc_city' , '0,'+id , cityid);
			
			if (town) {
				loc.fillOption('loc_town' , '0,'+province+','+city , town);
			}
		}
		
	} else {
		loc.fillOption('loc_province' , '0');
	}
	var title	= ['省份' , '城市' , '区县'];
	$.each(title , function(k , v) {
		title[k]	= '<option value="">'+v+'</option>';
	})
	
	$('#loc_province').append(title[0]);
	$('#loc_city').append(title[1]);
	$('#loc_town').append(title[2]);
}

