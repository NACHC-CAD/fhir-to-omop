# --- 
#
# Script to show the dqd web application in a browser.
#
# Run the init-params.r script before running this.  
# 
# Run the run-dqd.r script at least once before running this.  
#
# ---

DataQualityDashboard::viewDqDashboard (
  jsonPath = file.path(outputFolder, outputFile)
)

