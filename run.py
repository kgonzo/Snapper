import subprocess
import sys
from PIL import Image

# Ensure that a test image is provided
if len(sys.argv) != 2:
    print('usage: python3 run.py /path/to/test_image')
    exit()

# Display prediction choice
img = Image.open(sys.argv[1])
img.show()

# Set image size
imgSize = "224"

# Set architecture
arch = "mobilenet_0.50_" + imgSize

# Retrain the model on MobileNet
'''
retrain = "python -m scripts.retrain \
  	--bottleneck_dir=tf_files/bottlenecks \
  	--how_many_training_steps=500 \
  	--model_dir=tf_files/models/ \
  	--summaries_dir=tf_files/training_summaries/" + arch + "\
  	--output_graph=tf_files/retrained_graph.pb \
  	--output_labels=tf_files/retrained_labels.txt \
  	--architecture=" + arch + "\
  	--image_dir=tf_files/fish_photos"
process = subprocess.Popen(retrain.split(), stdout=subprocess.PIPE)
output, error = process.communicate()
'''
# Take test picture form user
userInput = sys.argv[1]

# Make prediction
predict = "python -m scripts.label_image \
    	--graph=tf_files/retrained_graph.pb  \
    	--image=" + userInput
process = subprocess.Popen(predict.split(), stdout=subprocess.PIPE)
output, error = process.communicate()

# Convert bytes-like object to string
output = output.decode("utf-8")
#print(output)

# Extract the prediction with the highest confidence
predictions = output.split("\n")
prediction = predictions[3]
prediction = prediction.split()
prediction = prediction[0] + " " + prediction[1]

# Print regulation information
file = open("fishdata.txt", "r")
print("\n***Results:")
while True:
    line = file.readline()
    if prediction in line.lower():
        print(line.rstrip())
        for i in range(0,3):
            line = file.readline().rstrip()
            line = line.replace("_", " ")
            print(line)

        break
