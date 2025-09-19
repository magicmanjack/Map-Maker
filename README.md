# Map making tool.
A tool simple tool which is used to help in the creation of tilemaps for games.
<img width="1112" height="740" alt="Screenshot 2025-09-19 at 4 04 17 PM" src="https://github.com/user-attachments/assets/a2c5efca-577e-42a6-9f3b-adeccc834cd9" />
<img width="1112" height="740" alt="Screenshot 2025-09-19 at 4 03 53 PM" src="https://github.com/user-attachments/assets/f97d71b5-7b34-4f5c-a10f-6f4e4a68cb12" />
## Output
The tool outputs to a very simple txt file format. A simple small example of the file output would be:

```
5 5  
0 0 1 0 0  
0 0 1 0 0  
1 1 1 1 1  
0 0 1 0 1  
0 0 0 0 0
```
This represents a 5x5 map with a plus symbol made up of tiles with the id of 1.

## Quick set up.
Inside of the project root directory, make an out directory. Copy the resources folder into out. And then compile the source into out.
E.g. (in bash)
```
mkdir out
cp -R res out/
javac -d out src/**/*.java
java -cp out main.Main
```
