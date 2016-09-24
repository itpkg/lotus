import Ember from 'ember';


export default Ember.Route.extend({
  ajax: Ember.inject.service(),
  actions: {
    signIn() {
      var user = {
        email: this.get('controller.email'),
        password: this.get('controller.password')
      };
      this.get('ajax').post('/users/sign_in', {data: user})
        .then(function(rst) {
          console.log(rst);
        }).catch(function(e) {
          alert(e.message);
        });
    }
  }
});
