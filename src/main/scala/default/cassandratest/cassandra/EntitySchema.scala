/*
 * Copyright 2018 lre
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package default.cassandratest.cassandra

object EntitySchema {

  def entityCreateSQL(entity: String) =
    s"""CREATE TABLE IF NOT EXISTS \"${entity}\"(
    id TEXT PRIMARY KEY,
    alias TEXT,
    tags SET<TEXT>,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP)"""

  def entityTruncateSQL(table: String) = s"""TRUNCATE TABLE \"$table\""""
  def entityDropSQL(table: String)     = s"""DROP TABLE \"$table\""""
}
