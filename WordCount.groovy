import groovy.io.FileType

def input = null

while (!input?.equalsIgnoreCase("Q")) {

    String consoleDialog = bookList() +
            '\nPlease enter one of the above books for analysis OR enter Q to quit: '
    input = System.console().readLine consoleDialog

    if (bookList().contains(input)) {
        String book = new File("books/", (input + ".txt"))?.getText()
        Map<String, Integer> mappedWords = mapWordsWithCount(scrapeListWords(book), getStopWords())
        mappedWords?.entrySet()?.take(100)?.each { def entry ->
            println(entry.key + ": " + entry.value)
        }
    } else {
        (!input?.equalsIgnoreCase("Q")) ? println("Input " + input + " invalid. Please try again.") :
                println("Goodbye")
    }
}


Map<String, Integer> mapWordsWithCount(List<String> words, List<String> stopWords) {
    def wordMap = [:]
    for (String word : words) {
        def key = word.toLowerCase()
        if (stopWords?.contains(key)) {
            continue
        } else if (wordMap.containsKey(key)) {
            wordMap[key] += 1
        } else {
            wordMap[key] = 1
        }
    }
    return wordMap.toSorted { -it.value }
}

public List<String> scrapeListWords(String text) {
    return text?.split("(['-]\\W+|[^\\w'-]\\W*)")
}

protected String bookList(){
    def files = []
    new File("books/").eachFile(FileType.FILES) {
        String title = it.name.take(it.name.lastIndexOf('.'))
        if(!"stop-words".equalsIgnoreCase(title)) {
            files << title
        }
    }
    files.join(', ')
}

protected List<String> getStopWords(){
    List<String> stopWords = []
    def each = new File("books/", "stop-words.txt")?.readLines()?.each {
            if(!it?.contains('#')) {
            stopWords.add(it.trim().toLowerCase())
        }
    }
    return stopWords
}
