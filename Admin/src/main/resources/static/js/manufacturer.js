$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (manufacturer, status){
            $('#idEdit').val(manufacturer.id);
            $('#nameEdit').val(manufacturer.name);
            $('#addressEdit').val(manufacturer.address);
            $('#emailEdit').val(manufacturer.email);
        });
        $('#editModal').modal();
    });
});