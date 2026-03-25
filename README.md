# RegexAlt

> A compiler that transforms human-readable patterns into Java regular expressions

[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

## 🎯 The Problem

Traditional regular expressions are cryptic and hard to maintain:
```regex
^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$
```

What does this do? It's nearly impossible to tell at a glance.

## ✨ The Solution

RegexAlt lets you write patterns in plain English:
```
pattern email:
  one_or_more(alphanumeric)
  literal "@"
  one_or_more(letter)
  literal "."
  between(2, 4, letter)
```

Crystal clear! And it compiles to the exact same regex with **zero runtime overhead**.

## 🚀 Features

- ✅ **Human-Readable Syntax** - Write patterns anyone can understand
- ✅ **Zero Runtime Overhead** - Compiles to native Java `Pattern` objects
- ✅ **Type-Safe** - Compile-time validation catches errors early
- ✅ **Production-Ready** - Comprehensive test coverage
- ✅ **Easy Integration** - Works with any Java project

## 📦 Quick Start

### Installation

Clone and build:
```bash
git clone https://github.com/jashjop/regex-alt.git
cd regex-alt
mvn clean install
```

### Usage
```java
import com.regexalt.RegexAlt;
import java.util.regex.Pattern;

public class Example {
    public static void main(String[] args) {
        String source = """
            pattern email:
              one_or_more(alphanumeric)
              literal "@"
              one_or_more(letter)
              literal "."
              between(2, 4, letter)
            """;
        
        Pattern pattern = RegexAlt.compilePattern(source, "email");
        
        System.out.println(pattern.matcher("user@example.com").matches()); // true
        System.out.println(pattern.matcher("invalid@").matches());         // false
    }
}
```

## 📚 Language Reference

### Character Classes

| RegexAlt | Regex | Description |
|----------|-------|-------------|
| `digit` | `\d` | Any digit (0-9) |
| `letter` | `[a-zA-Z]` | Any letter |
| `uppercase` | `[A-Z]` | Uppercase letters |
| `lowercase` | `[a-z]` | Lowercase letters |
| `alphanumeric` | `[a-zA-Z0-9]` | Letters and digits |
| `whitespace` | `\s` | Whitespace characters |
| `any` | `.` | Any character |

### Quantifiers

| RegexAlt | Regex | Description |
|----------|-------|-------------|
| `exactly(n, pattern)` | `{n}` | Exactly n occurrences |
| `at_least(n, pattern)` | `{n,}` | n or more occurrences |
| `between(n, m, pattern)` | `{n,m}` | Between n and m occurrences |
| `optional(pattern)` | `?` | Zero or one occurrence |
| `one_or_more(pattern)` | `+` | One or more occurrences |
| `zero_or_more(pattern)` | `*` | Zero or more occurrences |

### Literals
```
literal "text"    # Matches exact text
literal "@"       # Matches special characters
```

### Groups & Alternation
```
pattern protocol:
  either:
    "http"
    "https"
    "ftp"
```

## 🎨 Examples

### Email Validation
```
pattern email:
  one_or_more(alphanumeric)
  literal "@"
  one_or_more(letter)
  literal "."
  between(2, 4, letter)
```

### Phone Number (US Format)
```
pattern phone:
  exactly(3, digit)
  literal "-"
  exactly(3, digit)
  literal "-"
  exactly(4, digit)
```

### URL with Protocol
```
pattern url:
  either:
    "http"
    "https"
  literal "://"
  one_or_more(any)
```

### Strong Password (8+ characters)
```
pattern password:
  at_least(8, any)
```

### Date (YYYY-MM-DD)
```
pattern date:
  exactly(4, digit)
  literal "-"
  exactly(2, digit)
  literal "-"
  exactly(2, digit)
```

## 🏗️ Architecture

RegexAlt implements a **5-phase compiler pipeline**:
```
Source Code → Lexer → Parser → AST Builder → Code Generator → Java Pattern
```

### Design Patterns

- **Composite Pattern** - AST node hierarchy
- **Visitor Pattern** - Tree traversal and code generation
- **Builder Pattern** - AST construction
- **Strategy Pattern** - Different quantifier implementations
- **Factory Pattern** - Node creation

### Technology Stack

- **Java 25** - Programming language
- **ANTLR4 4.13.1** - Parser generator
- **Maven 3.9+** - Build automation
- **JUnit 5** - Testing framework

## 📁 Project Structure
```
regex-alt/
├── src/
│   ├── main/
│   │   ├── java/com/regexalt/
│   │   │   ├── RegexAlt.java           # Public API
│   │   │   ├── Examples.java           # Demo code
│   │   │   ├── ast/                     # AST node classes
│   │   │   │   ├── ASTNode.java
│   │   │   │   ├── CharClassNode.java
│   │   │   │   ├── LiteralNode.java
│   │   │   │   ├── QuantifierNode.java
│   │   │   │   ├── GroupNode.java
│   │   │   │   └── PatternNode.java
│   │   │   └── compiler/                # Compiler components
│   │   │       ├── RegexAltCompiler.java
│   │   │       ├── ASTBuilder.java
│   │   │       ├── CompilerException.java
│   │   │       └── CompilerErrorListener.java
│   │   └── antlr4/com/regexalt/
│   │       └── RegexAlt.g4             # Grammar specification
│   └── test/
│       └── java/com/regexalt/
│           └── CompilerTest.java       # Unit tests
├── examples/
│   └── patterns.txt                    # Example patterns
├── pom.xml                             # Maven configuration
└── README.md                           # This file
```

## 🧪 Running Tests
```bash
mvn test
```

## 🔨 Building
```bash
mvn clean package
```

The compiled JAR will be available at `target/regex-alt-1.0.0.jar`

## 🎯 Running Examples

### From Source
```bash
mvn exec:java -Dexec.mainClass="com.regexalt.Examples"
```

### From JAR
```bash
java -cp target/regex-alt-1.0.0.jar com.regexalt.Examples
```

## 📊 Performance

RegexAlt achieves **zero runtime overhead** by compiling patterns at build time:

- **Compilation:** Happens once during build
- **Runtime:** Uses native Java `Pattern` objects
- **Performance:** Identical to hand-written regex

## 🤝 Contributing

Contributions are welcome! Areas for improvement:

- [ ] Add lookahead/lookbehind support
- [ ] Implement named capture groups
- [ ] Add character ranges (e.g., `range('a', 'z')`)
- [ ] Support for anchors (`start_of_line`, `end_of_line`)
- [ ] Create VSCode extension with syntax highlighting



## 🙏 Acknowledgments

- **ANTLR4** - Parser generator framework
- **Maven** - Build automation
- **JUnit** - Testing framework


---

⭐ **If you find this project useful, please consider giving it a star!** ⭐
