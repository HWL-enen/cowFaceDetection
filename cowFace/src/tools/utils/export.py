
# pip install ultralytics


from ultralytics import YOLO

model = YOLO(r'D:/Downloads/ymodel.pt')

model.export(format='onnx')