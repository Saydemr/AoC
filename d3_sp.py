import math

def elf_score(char):
    if char.isupper():
        return ord(char) - 38
    else:
        return ord(char) - 96


score = 0
with open('input3.txt', 'r') as f:
    count = 1
    prev2 = []
    for line in f:
        together = ""
        line = line.strip()

        if count % 3 != 0:
            prev2.append(line)
            count += 1
            continue
        else:
            for i in range(len(line)):
                if line[i] in prev2[0] and line[i] in prev2[1]:
                    together += line[i]
                    break
            
            prev2 = []
            count += 1
        score += elf_score(together)

print(score)
