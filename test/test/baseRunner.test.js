const
  mockrequire = require('mock-require'),
  sinon = require('sinon'),
  should = require('should'),
  TestResult = require('../lib/helpers/testResult');

describe('BaseRunner', () => {
  let
    BaseRunner,
    runner,
    snippetMock;

  beforeEach(() => {
    BaseRunner = require('../lib/runners/baseRunner');

    snippetMock = {
      checkIfTodo: sinon.stub(),
      checkIfWontDo: sinon.spy(),
      render: sinon.spy(),
      clean: sinon.spy(),
      saveRendered: sinon.spy(),
      hooks: {},
      snippetFile: ''
    };

    runner = new BaseRunner();
  });

  afterEach(() => {
    mockrequire.stopAll();
  });

  describe('#run', () => {
    it('execute each step to test and lint a snippet', async () => {
      runner.lintExpect = sinon.spy();
      runner.runExpect = sinon.spy();

      await runner.run(snippetMock);

      should(snippetMock.checkIfTodo).be.calledOnce;
      should(snippetMock.checkIfWontDo.calledAfter(snippetMock.checkIfTodo)).be.eql(true);
      should(snippetMock.render.calledAfter(snippetMock.checkIfWontDo)).be.eql(true);
      should(runner.lintExpect.calledAfter(snippetMock.render)).be.eql(true);
      should(runner.runExpect.calledAfter(runner.lintExpect)).be.eql(true);
      should(snippetMock.clean.calledAfter(runner.runExpect)).be.eql(true);
    });
  });

  it('should save rendered snippet and throw exception on error', async () => {
    runner.lintExpect = sinon.spy();
    runner.runExpect = sinon.stub().throws(new Error('runExpect error'));

    try {
      await runner.run(snippetMock);
    } catch (e) {
      should(e).be.instanceOf(Error);
      should(snippetMock.saveRendered).be.calledOnce;
    }
  });
});
