# Working with threads
## InterthreadDialogWithCounter-Multithreading
### Description
Modify the code for the threads from the <a href="https://github.com/imLIVI/InterthreadedDialogue-Multithreading.git">previous task</a> so that it is a ```Callable```
task that returns the number of messages output to the console, and the execution cycle is finite. Run a certain number of these tasks into a pool of threads of a fixed 
number and get the results of the tasks. Perform a similar operation, but with the result from one of the tasks (```pool.invokeAny()```)

The functionality of the program:
1. Creating a few threads, each of which has its own name.
2. The thread prints a message to the console. The name of the stream must necessarily appear in the message
