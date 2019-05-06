<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.vendor.model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="author" content="Colorlib">
    <meta name="description" content="#">
    <meta name="keywords" content="#">
    <!-- Favicons -->
    <link rel="shortcut icon" href="#">
    <!-- Page Title -->
    <title>SeekFoodTable &amp; 搜尋結果</title>
    <!-- Bootstrap CSS -->
    <!-- 貓頭鷹 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- 以下是 自訂的東西 -->
   
    <!-- 線條樣式 -->
    <link rel="stylesheet" type="text/css" href="../front-end/css/simple-line-icons.css">
    <!-- Icon資源池 -->
    <link rel="stylesheet" type="text/css" href="../front-end/css/themify-icons.css">
    <!-- 動態效果 -->
    <link rel="stylesheet" type="text/css" href="../front-end/css/set1.css">
    <!-- 自訂 CSS主檔 -->
    <link rel="stylesheet" type="text/css" href="../front-end/css/style.css">
     <!-- 星星圖庫來源樣式 -->
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../css/starrr.css">
</head>
<style type="text/css">
body{
font-family:"微軟正黑體";
}

.featured-place-wrap img {
    width: 30%;

    object-fit: cover;
}

.featured-responsive .img-fluid {
    height: 240px;
    width: 300px;
    object-fit: cover;
    margin-top: 1%;
}

.featured-place-wrap .media {
    margin: 30px 0 10px 0
}

.memshow{
	height:64px!important;
	width:64px!important;
	object-fit: fill;
}

.featured-title-box{
	width:35%;
	height:30%;

}
.featured-place-wrap a{
	display: inline;
	cursor: default;
}

.starbox{
    padding: 20px;
    box-sizing: border-box;
}
</style>

<body>
    <!--============================= DETAIL =============================-->
    <section>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-7 responsive-wrap">
                    <div class="row detail-filter-wrap">
                        <div class="col-md-7 featured-responsive">
                            <div class="detail-filter-text">
                            <form  METHOD="post" ACTION="<%=request.getContextPath()%>/Vendor/Vendor.do" >
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="輸入條件.." aria-label="Recipient's username" aria-describedby="button-addon2" name="v_name" id="text_vendor" >
                                    <div class="input-group-append">
                                    <input type="hidden" name="action" value="search">
                                    <input type="hidden" name="scoreSelect" value="0">
                                        <button class="btn btn-warning" type="submit" id="button-addon2"><span class="icon-magnifier search-icon"></span>再次搜尋</button>
                                        <p>${fn:length(searchMap)}個符合條件的結果 <span>於${fn:length(alllist)}間餐廳</span></p>
                                    </div>
                                </div>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-5 featured-responsive">
                            <div class="detail-filter">
                                <p>變更條件</p>
                                <form class="filter-dropdown"  METHOD="post" ACTION="<%=request.getContextPath()%>/Vendor/Vendor.do">
                                    <select class="custom-select mb-2 mr-sm-2 mb-sm-0" name="scoreSelect" id="scoreSelect">
                                        <option selected>評分高於</option>
                                        <option value="4">4</option>
                                        <option value="3">3</option>
                                        <option value="2">2</option>
                                        <option value="2">1</option>
                                    </select>
                                    <input type="hidden" name="action" value="search">
                                    <input type="hidden" name="v_name" id="outerTxt" value="${param.v_name}">
                                </form>
                                <form class="filter-dropdown">
                                    <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="rangeSelect">
                                        <option selected>距離我</option>
                                        <option value="1">5公里</option>
                                        <option value="2">10公里</option>
                                        <option value="6">30公里</option>
                                        <option value="12">30公里以上</option>
                                    </select>
                                </form>
                                <div class="map-responsive-wrap">
                                    <a class="map-icon" href="#"><span class="icon-location-pin"></span></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row detail-checkbox-wrap">
                        <div class="col-6">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
                                <label class="form-check-label" for="inlineCheckbox1">營業中</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="option2">
                                <label class="form-check-label" for="inlineCheckbox2">有停車位</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="option3" disabled>
                                <label class="form-check-label" for="inlineCheckbox3">disabled</label>
                            </div>
                            核取方塊的位置
                        </div>
                        <div class="col-12">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">上頁</a>
                                    </li>
                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item"><a class="page-link" href="#">4</a></li>
                                    <li class="page-item"><a class="page-link" href="#">5</a></li>
                                    <li class="page-item">
                                        <a class="page-link" href="#">下頁</a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>


<c:forEach var="sMap" items="${searchMap}">

                    
                    <div class="container-fluid onerest">
                        <div class="col-12 featured-responsive ">
                            <div class="featured-place-wrap">
                                <div class="d-flex">
                                <img  class="img-fluid resultpic" alt="#" src="<%= request.getContextPath()%>/ShowImg.do?vendor_no='${sMap.key.vendor_no}'&pic=1">
                                                                        <span class="featured-rating-green">${sMap.value[0]}</span>
                                    <div class="featured-title-box">
                                        <h5>${sMap.key.v_name}</h5>
                                        <p>${sMap.key.v_type} </p> <span>• </span>
                                        <p>${sMap.value[1]} 評論</p> <span> • </span>
                                        <p><span>$$$</span>$$</p>
                                        <ul>
                                            <li><span class="icon-location-pin"></span>
                                                <span>${sMap.key.v_ad_code}${sMap.key.v_address1}${sMap.key.v_address2}${sMap.key.v_address3}</span>
                                            </li>
                                            <li><span class="icon-screen-smartphone"></span>
                                                <p>${sMap.key.v_n_code}-${sMap.key.v_tel}</p>
                                            </li>
                                            <li><span class="icon-info"></span>
                                                <p>座位數：${sMap.key.v_tables}</p>
                                            </li>
                                        </ul>
                                        <div class="bottom-icons">
                                            <div class="closed-now">休息中</div>
                                            <span class="ti-heart"></span>
                                            <span class="ti-share"></span>
                                        </div>
                                    </div>
                                    <div class="col-5">
                                        <div class="media">
                                        	
                                           <img src="<%= request.getContextPath()%>/ShowImg.do?mem_no='${sMap.value[4]}'" class="mr-3 memshow" alt="..."> 
                                          
                                            <div class="media-body">
                                                <h5 class="mt-0">我沒鼻子都覺得香</h5>
                                                ${sMap.value[2]}
                                            </div>
                                        </div>
                                        <c:if test="${ not empty sMap.value[3] }">
                                        <div class="starbox"><span style="font-size:20px">評分：</span><div class='starrr showstar' id="s${sMap.value[5]}"></div></div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
  
</c:forEach>

 


                    <div class="col-md-5 responsive-wrap map-wrap">
                        <div class="map-fix">
                            <!-- data-toggle="affix" -->
                            <!-- Google map will appear here! Edit the Latitude, Longitude and Zoom Level below using data-attr-*  -->
                            <div id="map" data-lat="24.969" data-lon="121.191" data-zoom="14"></div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
           
    </section>
    <!--//END DETAIL -->
    <!-- jQuery, Bootstrap JS. -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="../front-end/js/jquery-3.3.1.min.js"></script>
    <script src="../front-end/js/popper.min.js"></script>
	<!-- 星星 -->
    <script src="../js/starrr.js"></script>
    <!-- 貓頭鷹 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
    <script>
    $(".map-icon").click(function() {
        $(".map-fix").toggle();
    });
    </script>
    
    
   <script>

      var map;
      var service;
      var infowindow;

      function initMap() {
        var tibami = new google.maps.LatLng(24.9678012, 151.195);

        infowindow = new google.maps.InfoWindow();

        map = new google.maps.Map(
            document.getElementById('map'), {center: tibami, zoom: 13});

        searched();
  
      }
      
    
    </script>
    
     
		    <script>
		    

		    function searched(){
<c:forEach var="sMap" items="${searchMap}">  
		  	  var request${sMap.key.vendor_no} = {
		  	          query: '${sMap.key.v_address1}${sMap.key.v_address2}${sMap.key.v_address3}',
		  	          fields: ['name', 'geometry'],
		  	        };
		
		  	        service = new google.maps.places.PlacesService(map);
		
		  	        service.findPlaceFromQuery(request${sMap.key.vendor_no}, function(results, status) {
		  	          if (status === google.maps.places.PlacesServiceStatus.OK) {
		  	            for (var i = 0; i < results.length; i++) {
		  	              createMarker${sMap.key.vendor_no}(results[i]);
		  	            }
		
		  	            map.setCenter(results[0].geometry.location);
		  	          }
		  	        });
		  	        
		  	
		    }
		    
		
		    function createMarker${sMap.key.vendor_no}(place) {
		      var marker${sMap.key.vendor_no} = new google.maps.Marker({
		    	
		    	title: '${sMap.key.v_name}',
		        position: place.geometry.location,
		        draggable: false,
		        animation: google.maps.Animation.DROP
		        
		        
		      });
		
		      google.maps.event.addListener(marker${sMap.key.vendor_no}, 'click', function() {
		        infowindow.setContent('${sMap.key.v_name}');
		        infowindow.open(map, this);
		      });
		      marker${sMap.key.vendor_no}.addListener('click', toggleBounce);
		      marker${sMap.key.vendor_no}.setMap(map);
		      
		      function toggleBounce() {
			        if (marker${sMap.key.vendor_no}.getAnimation() !== null) {
			          marker${sMap.key.vendor_no}.setAnimation(null);
			        } else {
			          marker${sMap.key.vendor_no}.setAnimation(google.maps.Animation.BOUNCE);
			        }
			      }
</c:forEach> 
 
 yourplace();
 
		    }

		    </script>

			<script type="text/javascript">
			
			function yourplace(){
				
				infoWindow = new google.maps.InfoWindow;
			    var test = null;
		        // Try HTML5 geolocation.
		        if (navigator.geolocation) {
		          navigator.geolocation.getCurrentPosition(function(position) {
		            var pos = {
		              lat: position.coords.latitude,
		              lng: position.coords.longitude
		            };

		            var marker = new google.maps.Marker({
		            	draggable: true,
	                    position: pos,
	                    icon: {
	                        path: google.maps.SymbolPath.CIRCLE,
	                        fillColor: '#0000b3',
	                        fillOpacity: 1,
	                        strokeColor: '#FFFFFF',
	                        strokeWeight: 2,
	                        scale: 10
	                     },
	                    map: map
	                });
		            
		            setInterval(function() {
			        	   if (marker.getOpacity() == 1) {
			        	      marker.setOpacity(0);
			        	   } else {
			        	      marker.setOpacity(1);
			        	   }
			        	}, 700);
		            
		            infoWindow.open(map);
		            map.setCenter(pos);
		            
		           
		            
		            function circlesize(radius) {   
		            		
		            var sunCircle = {

		                    strokeColor: "#4CAF50",
		                    strokeOpacity: 0.8,
		                    strokeWeight: 2,
		                    fillColor: "#4CAF50",
		                    fillOpacity: 0.25,
		                    map: map,
		                    center: pos,
		                    radius: radius // in meters
		          
		                };
		            if (test != null){
		            	test.setMap(null);
		            }
		            
		            cityCircle = new google.maps.Circle(sunCircle);
		            test =cityCircle;
		        
	                cityCircle.bindTo('center', marker, 'position');
	                
	                google.maps.event.addListener(marker, 'dragend', function() {
	                	console.log(position)
	                	$.ajax({
	        	    		url: "<%=request.getContextPath()%>/MySearch",
	        	            type : 'post',
	        				data: { action: 'ajaxStatu', 
	        				menu_no:'${rmVO.menu_no}',
	        				menu_stat: 1},
	        				dataType: 'json',
	        				async : false,//同步請求
	        				cache : false,//不快取頁面
	        				
	        	    	})
	                });
	              }
		      
		        	$('#rangeSelect').change(function(){
		        	
		        		 
						  var rrr = $('#rangeSelect').val();
						  circlesize(rrr*5000);
						  circle.setMap(null);
					}); 
					      
		            
		            
		          }, function() {
		            handleLocationError(true, infoWindow, map.getCenter());
		          });
		        } else {
		          // Browser doesn't support Geolocation
		          handleLocationError(false, infoWindow, map.getCenter());
		        }
				
		        
			}
			
			
			
			</script>
			
			<script>
			$(function() {
			    $('#scoreSelect').change(function() {
			    	
			    	
			    	this.form.submit();
			    });
			});
			
			</script>
			

<c:forEach var="sMap" items="${searchMap}">    
    <script type="text/javascript">
    $('#s${sMap.value[5]}').starrr({
    	
    	max: 5,
    	rating:${sMap.value[3]},
    	readOnly: true,
    	emptyClass: 'fa fa-star-o',
        fullClass: 'fa fa-star'
        
      });
    
    </script>
</c:forEach>
    <!-- Map JS (Please change the API key below. Read documentation for more info) -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBYZhprf58VI160spKuA98fVS9AcSeVuVg&libraries=places&callback=initMap" async defer></script>
</body>

</html>