 var $cssButtons=$(".btn-block");
 var result={
   topRight:"",
   topLeft :"",
   botRight:"",
   botLeft:""
 };
 var boxShadow={
  inset:"",
  horrizontalLenght:"",
  verticalLength: "",
  blurRadius:"",
  spread:"",
  RGBA:'',
  hex:""
};
var textShadow={
  horizontalLenght:"",
  verticalLength:"",
  blurRadius:"",
  shadowColor:""
};
var rgbaObject={
  red:"",
  green:"",
  blue:"",
  opacity:""
};
var fontFace={
  fontFamily:"",
  fontName:""
};
var mutlipleColumns={
  columntCount:"",
  columnGap:""
};
var boxResize={
  value:""
};
var outlineObject={
  outlineThickness:"",
  outline:"",
  outlineColor:"",
  outlineOffset:""
};
var transition={
  property:"",
  duration:"",
  time:"",
  function:""

};
var transform={
  scale:"",
  rotate:"",
  translateX:"",
  transalteY:"",
  skewX:"",
  skewY:""
};

$(".save-btn").on("click", function() {
    var category = prompt("Would you like to save ?");
    console.log(category);
    if (category != null) {
    $("#border-radius-style").append("<option>"+category+"</option>");
  }

});
$(document).ready(function(){
	  $("#myBtn").click(function(){
	        $("#myModal2").modal({backdrop: false});
	    });
});

function PrintTransform()
{
  $("#transform-result-area").html("transform: "+ "scale"+"(" + transform.scale+")"
  + " "+ "(" + transform.rotate+"deg)" + " "+ "(" + transform.translateX+"px)"+ " "
  + "(" + transform.translateY+"px)"
  + " "+ "(" + transform.skewX+"deg)"
  + " "+ "(" + transform.skewY+"deg)");
}
function PrintBoxShadow()
{
  $("#boxShadow-result-area").html("box-shadow:" +boxShadow.inset
  + " "+ boxShadow.horrizontalLenght+ "px" + " "+boxShadow.verticalLength
  + "px" +" "+ boxShadow.blurRadius + "px"  +" "+ boxShadow.spread+ "px"+ " "+ "Hex:" + "#"+boxShadow.hex);
}
function  PrintOutline()
{
  $("#outline-result-area").html("outline:" +outlineObject.outlineThickness+"px"+' ' +
  outlineObject.outline  + ' '+ "#"+ outlineObject.outlineColor+ ";"
   + '\r'+  "outline-offset:"+ outlineObject.outlineOffset+ "px;");
}


$(".border-radius-input").on("input",function(){
var $this=$(this);

  if ($this.attr("tabindex") === "1"  ) {
  result.topLeft=$this.val();
  }
    if ($this.attr("tabindex") === "2") {
    result.topRight = $this.val();
    }
    if ($this.attr("tabindex") == "3") {
  result.botRight = $this.val();
    }
    if ($this.attr("tabindex") == "4") {
  result.botLeft = $this.val();
    }
    $("#border-result-area").html("border-radius:" +result.topRight + "px" + " "+ result.topLeft + "px"
   + " " + result.botRight + "px" + " " + result.botLeft + "px");
});



$(".box-shadow-input").on("input", function() {
  var $this=$(this);
  if ($("#box-shadow-color :selected").text()=="RGBA") {

  }
    if ($this.attr("tabindex") === "6"  ) {
    boxShadow.horrizontalLenght=$this.val();
    }
      if ($this.attr("tabindex") === "7") {
      boxShadow.verticalLength = $this.val();
      }
      if ($this.attr("tabindex") == "8") {
    boxShadow.blurRadius = $this.val();
      }
      if ($this.attr("tabindex") == "9") {
    boxShadow.spread = $this.val();
      }
      if ($this.attr("tabindex")== "15") {
        boxShadow.hex= $this.val();
      }
PrintBoxShadow();
  });

  $("#box-shadow-inset").on("change", function() {
    var $this=$(this);
    boxShadow.inset=$this.val();
  PrintBoxShadow();
  });


  $("#box-shadow-color").on("change", function() {
    var $this=$(this);
    var rgba=$("#box-shadow").children(".hidden")[0];
    var hex=$("#box-shadow").children(".hidden")[1];
    var jhex=$(hex);
    var jrgba=$(rgba);

  if ($this.val()=="rgba") {
  console.log($this.val());
  jrgba.removeClass("hidden").addClass("otherclass");

  }
  if ($this.val()=="hex") {
  console.log($this.val());
  jhex.removeClass("hidden").addClass("otherclass");
  jrgba.removeClass("otherclass").addClass("hidden");
  }
  });

$(".back-btn").on("click",function() {

  $cssButtons.css("visibility","initial");
  $(".interaction-area").css("visibility","hidden");
});

$(".text-shadow-input").on("input",function(){
var $this=$(this);

  if ($this.attr("tabindex") === "16"  ) {
  textShadow.horizontalLenght=$this.val();
  console.log($this.val());
  }
    if ($this.attr("tabindex") === "17") {
    textShadow.verticalLength = $this.val();
  console.log($this.val());
    }
    if ($this.attr("tabindex") == "18") {
  textShadow.blurRadius = $this.val();
  console.log($this.val());
    }
    if ($this.attr("tabindex") == "19") {
      console.log($this.val());
  textShadow.shadowColor = $this.val();
    }

    $("#text-shadow-result-area").html("text-shadow: " +textShadow.horizontalLenght + "px" + " "
      +  textShadow.verticalLength + "px"
  + ' ' +    textShadow.blurRadius + "px"
+ ' '  + "#"+textShadow.shadowColor +";");
});

$(".back-btn").on("click",function() {

  $cssButtons.css("visibility","initial");
  $(".interaction-area").css("visibility","hidden");
});

$(".rgba-input").on("input",function(){
var $this=$(this);

  if ($this.attr("tabindex") === "20"  ) {
  rgbaObject.red=$this.val();

  }
    if ($this.attr("tabindex") === "21") {
    rgbaObject.green = $this.val();

    }
    if ($this.attr("tabindex") == "22") {
  rgbaObject.blue = $this.val();

    }
    if ($this.attr("tabindex") == "23") {

  rgbaObject.opacity = $this.val();
    }

    $("#rgba-result-area").html("background-color: rgba (" +rgbaObject.red + ", " + ''
      +   rgbaObject.green + ", "
   +  rgbaObject.blue + ", "  + rgbaObject.opacity+ ");");
});

$(".font-face-input").on("input",function(){
var $this=$(this);

  if ($this.attr("tabindex") === "24"  ) {
  fontFace.fontFamily=$this.val();
  }
    if ($this.attr("tabindex") === "25") {
    fontFace.fontName= $this.val();
    }

    $("#font-face-result-area").html("@font-face {\rfont-family: " +fontFace.fontFamily+";" + '\r'
      + "src: "+ "url(" + fontFace.fontName+ ");");
});
$(".multiple-columns-input").on("input",function(){
var $this=$(this);
  if ($this.attr("tabindex") === "26"  ) {
  mutlipleColumns.columntCount=$this.val();
  }
    if ($this.attr("tabindex") === "27") {
    mutlipleColumns.columnGap= $this.val();
    }
    $("#multiple-columns-result-area").html("column-count: " +  mutlipleColumns.columntCount + ";"+  '\r'
      + "column-gap: " + mutlipleColumns.columnGap+ "px;");
});

$(".outline-input").on("input",function(){
var $this=$(this);
  if ($this.attr("tabindex") === "29"  ) {
  outlineObject.outlineThickness=$this.val();

  }

    if ($this.attr("tabindex") === "31"  ) {
    outlineObject.outlineColor=$this.val();

    }
    if ($this.attr("tabindex") === "32"  ) {
    outlineObject.outlineOffset=$this.val();
    }
    PrintOutline();
});

$("#outline-style").on("change", function() {
  var $this=$(this);
  outlineObject.outline=$this.val();

  PrintOutline();
});


$("#box-resize-type").on("change", function() {
  var $this=$(this);
  boxResize.value=$this.val();
  $("#box-resize-result-area").html("resize:" + boxResize.value);
});
$

$("#transition-property").on("change", function() {
  var $this=$(this);
  transition.property=$this.val();
  PrintTransition();
});

$(".transition-input").on("input",function(){
var $this=$(this);
  if ($this.attr("tabindex") === "34"  ) {
  transition.duration=$this.val();
  }

PrintTransition();
});

$("#transition-function").on("change", function() {
  var $this=$(this);
  transition.function=$this.val();
  PrintTransition();
});
$("#transition-time").on("change",function() {
  var $this=$(this);
  transition.time=$this.val();
  PrintTransition();
});

function PrintTransition()
{
  $("#transition-result-area").html("transition:"+ " " +transition.property+ " "
  + transition.duration + transition.time + " " + transition.function+ ";");
}

$(".transform-input").on("input",function(){
var $this=$(this);
  if ($this.attr("tabindex") === "37"  ) {
  transform.scale=$this.val();
  }
  if ($this.attr("tabindex") === "38"  ) {
  transform.rotate=$this.val();
  }
  if ($this.attr("tabindex") === "39"  ) {
  transform.translateX=$this.val();
  }
  if ($this.attr("tabindex") === "40"  ) {
  transform.translateY=$this.val();
  }
  if ($this.attr("tabindex") === "41"  ) {
  transform.skewX=$this.val();
  }
  if ($this.attr("tabindex") === "42"  ) {
  transform.skewY=$this.val();
  }

PrintTransform();

});




$cssButtons.on("click",function(){

var $this=$(this);
if ($this.text() =="Border Radius") {
$("#border-radius").css("visibility","initial");
}
if ($this.text()=="Box Shadow") {
$("#box-shadow").css("visibility","initial");
}

if ($this.text()=="Text Shadow") {
$("#text-shadow").css("visibility","initial");
}
if ($this.text()=="RGBA") {
$("#rgba").css("visibility","initial");
}
if ($this.text()=="Font Face") {
  $("#font-face").css("visibility","initial");
}
if ($this.text()=="Multiple Colums") {
  $("#multiple-columns").css("visibility","initial");
}
if ($this.text()=="Box Resize") {
  $("#box-resize").css("visibility","initial");
}
if ($this.text()=="Outline") {
  $("#outline").css("visibility","initial");
}
if ($this.text()=="Transition") {
  $("#transition").css("visibility","initial");
}
if ($this.text()=="Transform") {
    $("#transform").css("visibility","initial");
}

$cssButtons.css("visibility","hidden");
});
