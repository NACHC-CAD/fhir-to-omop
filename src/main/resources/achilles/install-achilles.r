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

# ---
#
# Bootstrap using remotes
#
# ---

install.packages("remotes", repos = "https://cran.rstudio.com", INSTALL_opts = c("--no-multiarch"))

# ---
#
# Utility methods
#
# ---

PonosLibUtil <- {}

PonosLibUtil$showVersions <- function() {
  # show versions
  R.Version()
  system("java -version")
  getwd()
  # show the module list
  Strategus::getModuleList()
}

PonosLibUtil$packageVersionExists <- function (pkgName, pkgVersion) {
  tryCatch (
    {
      return(packageVersion(pkgName) == pkgVersion)
    },
    error = function(e) {
      return(FALSE)
    }
  )
}

PonosLibUtil$installFromCran <- function(pkgName, pkgVersion) {
  if (requireNamespace(pkgName, quietly = TRUE) == TRUE && PonosLibUtil$packageVersionExists(pkgName, pkgVersion)) {
    print(paste("Correct version of package already installed: ", pkgName, pkgVersion, sep = " "))
  } else {  
    print(paste("* * * Installing from CRAN:", pkgName, pkgVersion, sep = " "))
    if(pkgName == "remotes") {
      install.packages("remotes", repos = "https://cran.rstudio.com", INSTALL_opts = "--no-multiarch")  
    } else {
      remotes::install_version(pkgName, repos = "https://cran.rstudio.com", version = pkgVersion, upgrade = FALSE, INSTALL_opts = "--no-multiarch", )
    }
  }
}

PonosLibUtil$installFromGithub <- function(pkgName, pkgVersion) {
  if (requireNamespace(pkgName, quietly = TRUE) == TRUE && PonosLibUtil$packageVersionExists(pkgName, pkgVersion)) {
    print(paste("Correct version of package already installed: ", pkgName, pkgVersion, sep = " "))
  } else {  
    print(paste("* * * Installing from GitHub:", pkgName, pkgVersion, sep = " "))
    remotes::install_github(pkgName, ref=pkgVersion, upgrade = FALSE, INSTALL_opts = "--no-multiarch")
  }
}

# ---
#
# Installs
#
# ---

# install pkgbuild (you might need to run the following line individually)
PonosLibUtil$installFromCran("pkgbuild", "1.4.4")
library(pkgbuild)

# check pkgbuild
pkgbuild::check_build_tools()

# install sql renderer
PonosLibUtil$installFromCran("SqlRender", "1.18.0")
library(SqlRender)

# test that SqlRenderer was install and works
translate("SELECT TOP 10 * FROM person;", "postgresql")

# install achilles packages
# remotes::install_github("OHDSI/CohortMethod@391b499c9e162f9cbc7c273baeb17c9eb2fdf4a4", INSTALL_opts = c("--no-multiarch"))
# remotes::install_github("OHDSI/Achilles@a9144b2f3119055b76dfe8e9d798654558127fe3", INSTALL_opts = c("--no-multiarch"))
remotes::install_github("OHDSI/Achilles@v1.7.2", INSTALL_opts = c("--no-multiarch"))


# check that the Achilles library loads
library(Achilles)
