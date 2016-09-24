import Ember from 'ember';

export default Ember.Component.extend({
  ajax: Ember.inject.service(),
  model() {
    return this.get('ajax').request('/info');
  }
});
