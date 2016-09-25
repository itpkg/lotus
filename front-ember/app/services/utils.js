import Ember from 'ember';

export default Ember.Service.extend({  
  failed(e) {
    var msg = e.message;
    if (e.errors) {
      msg = e.errors.map(function(m) {
        return m.title;
      }).join("\n");
    }
    alert(msg);
  }
});
