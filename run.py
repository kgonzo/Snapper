import subprocess
import sys
import re

# Open a text file to print to
outFile = open("result.out", "w+")

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

# Print the prediction
print(prediction)

outFile.write(prediction)
outFile.close()
