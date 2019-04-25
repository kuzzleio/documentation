class TaxiController extends BaseController {
  constructor (kuzzle) {
    super(kuzzle, 'my-plugin/taxi');
  }

  enroll () {
    return this.query({
      action: 'enroll'
    });
  }
}

kuzzle.useController(TaxiController, 'taxi');

console.log(kuzzle.taxi.enroll());
