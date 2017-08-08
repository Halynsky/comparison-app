app.service('utils', function() {
  this.collapseInTree = function(event) {
    $(event.currentTarget)
        .parent()
        .parent()
        .toggleClass('in')
        .find('.tree-element-content')
        .first()
        .slideToggle(300)
        .toggleClass('in');

    event.stopImmediatePropagation();
  };
});