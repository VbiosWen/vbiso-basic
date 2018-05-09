function getDayOfYear(timeStamp) {
  var date=new Date();
  date.setTime(timeStamp);
  var time=date.toLocaleDateString();
  var times=time.split("/");
  var year=parseInt(times[0]);
  var month=parseInt(times[1]);
  var day=parseInt(times[2]);
  var isRun=isRin(year);
  var arr=[31,28,31,30,31,30,31,31,30,31,30,31]
  var sum=0;
  for(var i=0;i<month-1;i++){
    if(month==1){
      if(isRun){
        sum+=arr[i]+1;
      }
    }else{
      sum+=arr[i];
    }
  }
  sum+=day;
  return sum;
}

function isRin(year) {
  if((year%4==0)&&(year%100!=0||year%400==0)){
    return true;
  }else {
    return false;
  }
}