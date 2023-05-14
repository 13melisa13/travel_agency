var tours = /*[[${tours}]]*/ [];
    $( function() {
        
        
        let maxPriceTour, minPriceTour;
        for (let tour of tours) {
            
            if (maxPriceTour == null || parseInt(tour['price']) > parseInt(maxPriceTour['price'])){
                maxPriceTour = tour;
            }
            if (minPriceTour == null || parseInt(tour['price']) < parseInt(minPriceTour['price'])){
                minPriceTour = tour;
            }
        }
        let minPrice = parseInt(minPriceTour['price']); 
        let maxPrice = parseInt(maxPriceTour['price']);
      $( "#slider-range" ).slider({
        range: true,
        min: minPrice,
        max: maxPrice,
        values: [ minPrice, maxPrice ],
        slide: function( event, ui ) {
          $( "#amount" ).val( "$" + ui.values[0] + " - $" + ui.values[ 1 ] );
          console.log(ui.values[0]);
          console.log(tours.filter(tour => ui.values[0] >= parseInt(tour.price) &&  ui.values[1] <= parseInt(tour.price)));
        }
      });
      $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
        " - $" + $( "#slider-range" ).slider( "values", 1 ) );
    } );
    