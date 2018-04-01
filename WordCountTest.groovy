import groovy.util.GroovyTestSuite
import junit.framework.Test
import junit.framework.TestCase
import junit.textui.TestRunner

public class WordCount_Test extends TestCase{

    final WordCount classUnderTest = new WordCount()

    public void test_mapWordsWithCount_null_should_be_false(){
        assert !classUnderTest.mapWordsWithCount(null, null)
    }

    public void test_scrapeListWords_should_contain_contractions(){
        assert (classUnderTest.scrapeListWords("shouldn't, ")).contains("shouldn't")
    }

    public void test_scrapeListWords_should_return_words(){
        assert (classUnderTest.scrapeListWords("jet rocket ")).contains("jet")
    }

    public void test_scrapeListWords_should_false_for_null(){
        assert !(classUnderTest.scrapeListWords(null))
    }

    public void test_bookList_should_return_null_on_empty(){

    }

    public void test_getStopWords_contains_the() {
        //assert classUnderTest.getStopWords().contains("the")
    }

}

public class RunTests{
    static Test suite() {
        def allTests = new GroovyTestSuite()
        allTests.addTestSuite(WordCount_Test.class)
        return allTests
    }
}

TestRunner.run(RunTests.suite())
