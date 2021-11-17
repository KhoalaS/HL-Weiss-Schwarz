function getInventory(){
    $.getJSON('user/getInv', function (data){
        $("ul#output > li").remove();
            $.each(data, function (key, value) {
                 $("#output").append('<li>' + value.idol + '</li>');
                        });
    });
}