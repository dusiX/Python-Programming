import sys, random

class List():
    
    def __init__(self):
        pass

    def show_list(self):

        try:
            with open('Python\Mini-Project_To-Do-List\To_Do_List.txt', 'r') as stream:
                self.lines = stream.readlines()

            print("\n[YOUR TASKS]")
            if not self.lines:
                print("Empty list")
            else:
                for line in self.lines:
                    print(line.strip())
        except FileNotFoundError:
            print("\nNo task list found. Create one by addind tasks!")
        except Exception as e:
            print("\nSomething went wrong!\n", e)

    def add_task(self):

        print("\n[ADD TASK]")

        name = input("What is the task?\n")
        deadline = input("\nWhat is the deadline?\n")
        task = Task(name, deadline)

        try:
            with open('Python\Mini-Project_To-Do-List\To_Do_List.txt', 'a') as stream:
                stream.write(task.ID + ' | ' + task.name + ' | ' + task.deadline + '\n')
            print("\nTask added!")
        except Exception as e:
            print("\nSomething went wrong!\n", e)

    def complete_task(self):
        self.show_list()
        if self.lines == []:
            print("Nothing to be completed!")
            return
        
        completeID = ''

        while type(completeID) != int:
            try:
                completeID = int(input("\nEnder ID of the task you'd like to complete: "))
            except:
                print("Please provide a correct number.")

        updated_lines = [line for line in self.lines if not line.startswith(str(completeID))]

        if len(updated_lines) == len(self.lines):
            print("\nNo task with that ID found.")
            return
        else:
            self.lines = updated_lines
            try:
                with open('Python\Mini-Project_To-Do-List\To_Do_List.txt', 'w') as stream:
                    stream.writelines(self.lines)
                print("\nTask completed!")
            except Exception as e:
                print("\nSomething went wrong!\n", e)

    def exit(self):
        sys.exit()

class Task():

    def __init__ (self, name, deadline, ID = random.random()):
        self.ID = str(ID)[2:]
        self.name = name
        self.deadline = deadline

while True:

    print("\n== TODO LIST ==\n[1] show tasks\n[2] add task\n[3] complete task\n[4] exit")

    choice_list = [1, 2, 3, 4]
    choice = 0
    todo_list = List()

    while choice not in choice_list:
        try:
            choice = int(input("\nYour choice: "))
            if choice not in choice_list:
                print("\nPlease choose number from 1 to 4, where:\n[1] show tasks\n[2] add task\n[3] complete task\n[4] exit")
        except:
            print("\nPlease choose number from 1 to 4, where:\n[1] show tasks\n[2] add task\n[3] complete task\n[4] exit")

    match choice:
        case 1:
            todo_list.show_list()
        case 2:
            todo_list.add_task()
        case 3:
            todo_list.complete_task()
        case 4:
            todo_list.exit()