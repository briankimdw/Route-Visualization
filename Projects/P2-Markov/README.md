## Markov Text Generator (Java)

### Overview
Java implementation of a word-level Markov text generator. The project builds an immutable `WordGram` type and two generator implementations:

- `BaseMarkov`: baseline model that scans the training text on-the-fly
- `HashMarkov`: optimized model that precomputes a `HashMap<WordGram, List<String>>` for fast generation

Training data is included under `p2-markov/data/` (e.g., `alice.txt`, `shakespeare.txt`). The driver prints timing for training and generation.

### Key components
- `WordGram`: immutable sequence of words with correct `equals`, `hashCode`, `toString`, and `shiftAdd`
- `BaseMarkov`: reference implementation of the Markov generator
- `HashMarkov`: efficient generator that builds a follows map in one pass
- `MarkovDriver`: CLI entry point to train and generate text
- JUnit tests (`WordGramTest`, `MarkovTest`) with jars in `p2-markov/lib/`

### Quick start
Requirements: Java 11+ (tested with Java 17)

```bash
cd "p2-markov"
javac src/*.java
java -cp src MarkovDriver
```

Defaults (in `MarkovDriver`) use `HashMarkov`, seed 1234, order 2, and `data/shakespeare.txt`. Adjust constants at the top of `MarkovDriver` to change order, text size, seed, or dataset.

### Switch models
To compare models, toggle the generator in `MarkovDriver`:

```java
// MarkovInterface generator = new BaseMarkov(MODEL_ORDER);
MarkovInterface generator = new HashMarkov(MODEL_ORDER);
```

### Run tests (optional)
You can run tests in VS Code Test Explorer, or via console:

```bash
cd "p2-markov"
javac -cp lib/junit-jupiter-api-5.8.2.jar:lib/opentest4j-1.2.0.jar src/*.java
java -jar lib/junit-platform-console-standalone-1.8.2.jar -cp src --scan-classpath
```

### Data
Curated sample corpora live in `p2-markov/data/` (classic literature, speeches, etc.). Add your own plain-text file and point `MarkovDriver` at it via the `filename` variable.

### What I built (2023)
- Implemented `WordGram` and validated with unit tests
- Built `HashMarkov` to generate identical output to `BaseMarkov` with the same seed while improving generation speed
- Added a simple CLI driver with reproducible randomness and timing output

### License
Educational project based on a CS201 assignment. Provided here for learning and reference.


