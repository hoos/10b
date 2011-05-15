# VGLUE HTTP test script.
#
# A simple example using the HTTP plugin that shows the retrieval of a
# single page via HTTP. The resulting page is written to a file.
#

from net.grinder.script.Grinder import grinder
from net.grinder.script import Test
from mobi.hoos.engine import * 
from mobi.hoos.dataset import * 
from mobi.hoos.analyser import * 

test1 = Test(1, "Get DataSet")

class TestRunner:
    def __call__(self):
       tbEngineTest = TBEngineTest()
       tbEngineTest.setUp()
       request = test1.wrap(tbEngineTest)
       result = request.testGetDataSet()
       #result = test1.testGetDataSet()

