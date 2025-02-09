import random

suits = ['hearts', 'diamonds', 'clubs', 'spades']
values = ['Ace', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'Jack', 'Queen', 'King']

class Deck:
    def __init__(self, cards):
        self.cards = [Card(suit, value) for suit in suits for value in values]
    
    def shuffle(self):
        random.shuffle(self.cards)
    
    def deal(self):
        return self.cards.pop() if self.cards else None
    
    def count_remaining(self):
        return len(self.cards)
    
    def get_remaining(self):
        return [card.present() for card in self.cards]
    

class Card:
    def __init__(self, suit, value):
        if suit in suits:
            self.suit = suit
        else:
            raise ValueError("Incorrect suit provided. Please provide suit from the list: " suits)
        
        if value in values:
            self.value = value
        else:
            raise ValueError("Incorrect value provided. Please provide value from the list: " values)
    
    def present(self):
        return self.value + " of " + self.suit
    
