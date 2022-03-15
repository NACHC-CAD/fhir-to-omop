# ---
#
# install-dqd.r
#
# This script installs the components you will need to run and view the DQD.
# Run this script once.
# Your output should look similar to what is shown at the link below.  
# (also in the output folder of the directory where this script came from)
#
# ---

install.packages("devtools")
install.packages("rJava", INSTALL_opts = "--no-multiarch")
install.packages("shiny")
devtools::install_github("OHDSI/DatabaseConnector", INSTALL_opts = "--no-multiarch")
devtools::install_github("OHDSI/SqlRender", INSTALL_opts = "--no-multiarch")
devtools::install_github("OHDSI/DataQualityDashboard", INSTALL_opts = "--no-multiarch")




