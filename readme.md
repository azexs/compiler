### Wymagania:
* Java - > sudo apt install default-jdk
* Maven - > sudo apt install maven
### Kompilacja:
```
mvn clean install
```
W wygenerowanym folderze target/compiler znajduje się compiler.jar z kompilatorem. Uruchamiamy go:
```
java -jar compiler.jar <nazwa pliku wejściowego> <nazwa pliku wyjściowego>

```