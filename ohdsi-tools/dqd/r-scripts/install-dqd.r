# ---
#
# Data Quality Dashboard
#   NEED TO RUN RGUI AS ADMIN:
#   https://stackoverflow.com/questions/55803026/r-problem-updating-rlang-when-installing-dev-version-of-a-package 
#
# ---

install.packages("devtools")
install.packages("rJava", INSTALL_opts = "--no-multiarch")
devtools::install_github("OHDSI/DatabaseConnector", INSTALL_opts = "--no-multiarch")
devtools::install_github("OHDSI/SqlRender", INSTALL_opts = "--no-multiarch")
devtools::install_github("OHDSI/DataQualityDashboard", INSTALL_opts = "--no-multiarch")




