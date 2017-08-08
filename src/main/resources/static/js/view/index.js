var categories = [];
$(".MoreServices-service").each(function( index ) {
    var categoryElement = $(this);
    // console.log (categoryElement.find("h3").text());

    var category = {
        name: categoryElement.find("h3").text(),
        services: []
    };

    categoryElement.find("ul li").each(function( index ) {
        var serviceElement = $(this);

        console.log (JSON.stringify(serviceElement.find("a")[0].innerText));

        var service = {
            name: serviceElement.find("a")[0].innerText
        };

        category.services.push(service)

    });

    categories.push(category)

});

console.log(JSON.stringify(categories));