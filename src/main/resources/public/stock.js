$(document).ready(function() {
  $( "#stockTicker" ).submit(function( event ) {
    let symbol = $("#symbol").val();
    // regex to make sure that entered value is only alpha and dash
    if( !/^[a-z-]+$/i.test(symbol) ){
      alert('Please enter a valid Stock symbol');
      return;
    }
    //clear out old data
    $("#hi").empty();
    $("#low").empty();

    //query back end service for data
    $.ajax({
      url: "http://localhost:8080/stockTickerLookup?symbol=" + symbol,
    }).then(function(data, status, jqxhr) {
      $("#hi").append(data.highValue);
      $("#low").append(data.lowValue)
    });
    event.preventDefault();
  });
});