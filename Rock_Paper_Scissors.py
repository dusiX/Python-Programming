import random

class Computer():
    def __init__(self, score = 0):
        choice_list = ["r", "p", "s"]
        self.choice = random.choice(choice_list)
        self.score = score

class User():
    def __init__(self, choice, score = 0):
        self.choice = choice
        self.score = score

class Game():
    def __init__(self):
        pass

    def play(self):
        rounds = ''
        choice_list = ["r", "p", "s"]

        print("--- Rock Paper Scissors Game ---")

        while type(rounds) != int:
            try:
                rounds = int(input("How many rounds would you like to play?\n"))
            except:
                print("Please provide a correct number.")
        
        for i in range(rounds):
            user = User(input("\nRock, paper or scissors [r/p/s]?\n"))

            while user.choice not in choice_list:
                print("Please provide letter 'r' for rock, 'p' for paper or 's' for scissors.")
                user.choice = input("Rock, paper or scissors [r/p/s]?\n")
        
            computer = Computer()

            print("\nYou: " + user.choice + " | Computer: " + computer.choice)

            match(user.choice):
                case("r"):
                    match(computer.choice):
                        case("r"):
                            print("This round is a tie")
                        case("p"):
                            print("You lost this round!")
                            computer.score += 1
                        case("s"):
                            print("You won this round!")
                            user.score += 1
                case("p"):
                    match(computer.choice):
                        case("r"):
                            print("You won this round!")
                            user.score += 1
                        case("p"):
                            print("This round is a tie")
                        case("s"):
                            print("You lost this round!")
                            computer.score += 1
                case("s"):
                    match(computer.choice):
                        case("r"):
                            print("You lost this round!")
                            computer.score += 1
                        case("p"):
                            print("You won this round!")
                            user.score += 1
                        case("s"):
                            print("This round is a tie")

        print("\n[Game summary] Your points: " + str(user.score) + " | Computer points: " + str(computer.score))

        if user.score > computer.score:
            print("You won.")
        elif user.score < computer.score:
            print("Computer won.")
        else:
            print("It was a tie.")

game = Game()
game.play()        