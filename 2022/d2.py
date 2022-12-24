'''
A = rock : 1
B = paper : 2
C = scissors : 3
'''

points = {
    'A': 1,
    'B': 2,
    'C': 3
}

wins = {
    'A': 'B',
    'B': 'C',
    'C': 'A'
}

lose = {
    'A': 'C',
    'B': 'A',
    'C': 'B'
}


def what_to_play(opp, guide):
    if guide == 'Y':
        return opp
    elif guide == 'X':
        return lose[opp]
    elif guide == 'Z':
        return wins[opp]

score = 0
with open('input2.txt', 'r') as f:
    for line in f:
        opp, guide = line.strip().split()
        if guide == 'Y':
            score += 3
        elif guide == 'X':
            score += 0
        elif guide == 'Z':
            score += 6
        play = what_to_play(opp, guide)
        score += points[play]
        

print(score)



        
