# --- 
#
# Script to istall dependencies
# This script will istall the dependencies required to run Achilles.  
# This script only needs to be run once for a given R instance (computer).  
#
# ---

# install pkgbuild (you might need to run the following line individually)
install.packages("pkgbuild")

# check pkgbuild
pkgbuild::check_build_tools()

# install remotes
install.packages("remotes", INSTALL_opts = c("--no-multiarch"))

# install sql renderer
install.packages("SqlRender")
library(SqlRender)

# test that SqlRenderer was install and works
translate("SELECT TOP 10 * FROM person;", "postgresql")

# install achilles packages
remotes::install_github("OHDSI/CohortMethod", INSTALL_opts = c("--no-multiarch"))
remotes::install_github("OHDSI/Achilles", INSTALL_opts = c("--no-multiarch"))

# check that the Achilles library loads
library(Achilles)

