#Importing libraries
from pdf2image import convert_from_path
import os
import os.path
from PIL import Image

#Iterating through each page inj the pdf file
def convert_pdf_to_images(pdf_path):
    images = convert_from_path(pdf_path)
    for index, image in enumerate(images):
        image.save(f'output/{index}.jpg')

#Using the library in the module to convert pdf to png
def convert():
    # Specify the name of the PDF file here
    convert_pdf_to_images('myPDF.pdf')
   
#Function to crop the images    
def crop_center(pil_img, crop_width, crop_height):
    img_width, img_height = pil_img.size
    return pil_img.crop(((img_width - crop_width) // 2,
                         (img_height - crop_height) // 2,
                         (img_width + crop_width) // 2,
                         (img_height + crop_height) // 2))

def create_output_folder():
    # Directory
    directory = "output"
  
    # Parent Directory path
    parent_dir = os.getcwd()
  
    # Path
    path = os.path.join(parent_dir, directory)
  
    # Create the directory
    os.mkdir(path)
    print("Directory '% s' created" % directory)
    


#Main fucntion (converting pdf to png and then cropping png files 
#and replacing it with cropped files)
if __name__ == "__main__":

    create_output_folder()
    
    convert()
    print('convert success')
    
    f = os.getcwd()+'/output'
    # f = "/Users/zaidahamed/croppingImage/output"
    
    for file in os.listdir(f):
        # open 1 image 
        f_img = f+"/"+file
        img = Image.open(f_img)
        
        # perform action
        im_new = crop_center(img, 1050, 1050)
        
        # save changes
        im_new.save(f_img, quality=100)
    
    print("Crop success")
    


     