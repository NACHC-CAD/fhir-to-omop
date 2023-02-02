# --- 
#
# Script to install dependencies
# This script will install the dependencies required to run Achilles.  
# This script only needs to be run once for a given R instance (computer).  
#
# The SHA used for CohortMethod is for the 2022-08-14 commit.  
# The SHA used for Achilles is for the 2022-08-12 commit.  
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
remotes::install_github("OHDSI/CohortMethod@391b499c9e162f9cbc7c273baeb17c9eb2fdf4a4", INSTALL_opts = c("--no-multiarch"))
remotes::install_github("OHDSI/Achilles@a9144b2f3119055b76dfe8e9d798654558127fe3", INSTALL_opts = c("--no-multiarch"))

# check that the Achilles library loads
library(Achilles)

