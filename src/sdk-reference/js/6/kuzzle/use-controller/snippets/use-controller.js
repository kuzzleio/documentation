class TaxiController extends BaseController {
  constructor (kuzzle) {
    super(kuzzle, 'my-plugin/taxi');
  }

  enroll () {
    return 'Success';
  }
}

kuzzle.useController(TaxiController, 'taxi');

console.log(kuzzle.taxi.enroll());
