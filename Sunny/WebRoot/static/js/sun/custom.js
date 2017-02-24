/* 解决手机横屏时图片无法填充的问题 */

window.onload=function(){
  var d = document.getElementsByClassName("cover-img")[0];
  var h = d.height || d.style.height || d.offsetHeight; 
  //获取屏幕的的宽度
  if(document.body.scrollWidth > document.body.scrollHeight){
   //更改clss为totop
    d.setAttribute("class","totop");
  }else{
    d.setAttribute("class","toleft");
  }
}

