import os, os.path
import win32com.client

xl=win32com.client.Dispatch("Excel.Application")
xl.Visible = True
xl.Workbooks.Open(os.path.abspath("k:\chrome\Factorio_science.xls"), ReadOnly=1)