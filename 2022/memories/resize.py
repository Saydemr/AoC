from PIL import Image
from PIL.Image import Resampling
import os

mywidth = 1024

for image in os.listdir('.'):
    if image.endswith('.jpg'):
        img = Image.open(image)
        wpercent = (mywidth/float(img.size[0]))
        hsize = int((float(img.size[1])*float(wpercent)))
        img = img.resize((mywidth,hsize), Resampling.LANCZOS)
        img.save("_"+ image)
