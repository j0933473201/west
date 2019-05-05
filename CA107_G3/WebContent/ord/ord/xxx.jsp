<html>
<head>
<meta charset="utf-8">
<title>動態建立按鈕</title>
<script src='http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js'></script>
</head>
<body>
<a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow" id="AddMoreFileBox" class="btn btn-info">新增更多的input輸入框</a></span></p>
<div id="InputsWrapper">
<div><input type="text" name="mytext[]" id="field_1" value="Text 1"/><a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow" class="removeclass"><input type='button' value='刪除'></a></div>
</div>
<script>
$(document).ready(function() {
var MaxInputs    = 8; //maximum input boxes allowed
var InputsWrapper  = $("#InputsWrapper"); //Input boxes wrapper ID
var AddButton    = $("#AddMoreFileBox"); //Add button ID
var x = InputsWrapper.length; //initlal text box count
var FieldCount=1; //to keep track of text box added
$(AddButton).click(function (e) //on add input button click
{
if(x <= MaxInputs) //max input box allowed
{
FieldCount  ; //text box added increment
//add input box
$(InputsWrapper).append('<div><input type="text" name="mytext[]" id="field_'  FieldCount  '" value="Text '  FieldCount  '"/><a href="#" rel="external nofollow" rel="external nofollow" rel="external nofollow" class="removeclass"><input type="button" value="刪除"></a></div>');
x  ; //text box increment
}
return false;
});
$("body").on("click",".removeclass", function(e){ //user click on remove text
if( x > 1 ) {
$(this).parent('div').remove(); //remove text box
x--; //decrement textbox
}
return false;
})
});
</script>
</body>
</html>