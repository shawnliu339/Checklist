$(function() {

	var items = $(".list-group [name='percentage']");
	for (i = 0; i < items.length; i++) {
		var item = items[i];
		if ($(item).text() == 0)
			$(item).parent().addClass("list-group-item-danger");
		if ($(item).text() == 100)
			$(item).parent().addClass("list-group-item-success");
		if ($(item).text() > 0 && $(item).text() < 100) {
			$(item).parent().addClass("list-group-item-warning");
		}
	}

});